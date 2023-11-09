package com.unifacisa.usuarioservice.service;

import com.unifacisa.usuarioservice.domain.Acao;
import com.unifacisa.usuarioservice.repository.AcaoRepository;
import com.unifacisa.usuarioservice.service.dto.AcaoDTO;
import com.unifacisa.usuarioservice.service.mapper.AcaoMapper;
import com.unifacisa.usuarioservice.utils.CrudUtils;
import com.unifacisa.usuarioservice.utils.exceptions.CustomServerErrorException;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcaoService {

    @Autowired
    private AcaoRepository acaoRepository;

    public List<AcaoDTO> listar() {
        return acaoRepository.findAll()
                .stream()
                .map(AcaoMapper.INSTANTE::toDto)
                .collect(Collectors.toList());
    }

    public AcaoDTO salvar(Acao acao) {
        Acao novoAcao = acaoRepository.save(acao);
        return AcaoMapper.INSTANTE.toDto(novoAcao);
    }

    public List<AcaoDTO> buscar(Double valor) {
        List<AcaoDTO> acaoDTOS = acaoRepository.findByValorLessThanEqual(valor / 100)
                .stream()
                .map(AcaoMapper.INSTANTE::toDto)
                .collect(Collectors.toList());
        return acaoDTOS;

    }

    public void deletar(Long id) {
        this.acaoRepository.deleteById(id);
    }

    public void salvarAcoesCompraveis() throws Exception {
        List<Acao> acaoList = buscarAcoesCompraveis();
        acaoRepository.saveAll(acaoList);
    }

    public List<Acao> buscarAcoesCompraveis() throws Exception {
        JSONArray jsonArray = obterListaDeAcoes();
        List<Acao> acaoDTOS = new ArrayList<>();

        if (jsonArray != null) {
            obterCotacoesDasAcoes(jsonArray);
            System.out.println(jsonArray.toJSONString());

            for (Object acaoObj : jsonArray) {
                Acao acao = new Acao();
                JSONObject acaoJSON = (JSONObject) acaoObj;

                acao.setNome((String) acaoJSON.get("acao"));
                acao.setValor((Double) acaoJSON.get("valor"));
                acaoRepository.save(acao);
            }
        }

        return acaoDTOS;
    }

    public static JSONArray obterListaDeAcoes() throws Exception {
        String bovespaUrl = "https://opcoes.net.br/opcoes2/bovespa";
        Document bovespaDocument = Jsoup.connect(bovespaUrl).get();

        Element selectElement = bovespaDocument.select("select[name=IdAcao]").first();

        if (selectElement != null) {
            Elements options = selectElement.select("option");
            JSONArray jsonArray = new JSONArray();

            for (Element option : options) {
                String acaoNome = option.val();
                if (acaoNome != "") {
                    JSONObject jsonOption = new JSONObject();
                    jsonOption.put("acao", acaoNome);
                    jsonArray.add(jsonOption);
                }
            }
            return jsonArray;
        } else {
            System.out.println("Elemento <select> com name='IdAcao' não encontrado.");
            return null;
        }
    }

    public void obterCotacoesDasAcoes(JSONArray jsonArray) {
        String cotacaoBaseUrl = "https://opcoes.net.br/cotacoes?ativos=";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            for (Object acaoObj : jsonArray) {
                JSONObject acaoJSON = (JSONObject) acaoObj;
                String acaoNome = (String) acaoJSON.get("acao");

                String cotacaoUrl = cotacaoBaseUrl + acaoNome;
                HttpGet httpGet = new HttpGet(cotacaoUrl);

                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    JSONParser parser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) parser.parse(EntityUtils.toString(response.getEntity()));

                    JSONObject data = (JSONObject) jsonObject.get("data");
                    if (data.containsKey(acaoNome)) {
                        JSONObject acaoData = (JSONObject) data.get(acaoNome);

                        if (acaoData.containsKey("ULT")) {
                            Double valor = (Double) acaoData.get("ULT");
                            acaoJSON.put("valor", valor);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao obter cotação da ação: " + acaoNome);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao obter cotações de ações");
            e.printStackTrace();
        }
    }

}