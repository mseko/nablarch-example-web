package com.nablarch.example.app.web.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nablarch.example.app.entity.Industry;
import com.nablarch.example.app.web.dto.IndustryDto;
import nablarch.common.dao.UniversalDao;
import nablarch.core.beans.BeanUtil;
import nablarch.fw.ExecutionContext;
import nablarch.fw.jaxrs.ErrorResponseBuilder;
import nablarch.fw.web.HttpRequest;
import nablarch.fw.web.HttpResponse;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 業種検索API
 *
 * @author Nabu Rakutaro
 */
public class IndustryAction {

    /**
     * 業種一覧を取得する。
     *
     * @return 業種一覧
     */
    @Produces(MediaType.APPLICATION_JSON)
    public List<IndustryDto> find() {
        List<Industry> industries = UniversalDao.findAll(Industry.class);

        return industries
                .stream()
                .map(industry -> BeanUtil.createAndCopy(IndustryDto.class, industry))
                .collect(Collectors.toList());
    }

    /**
     * 常に404 Not Foundを返す。
     * @param request リクエスト
     * @param context コンテキスト
     * @return 404 Not Found
     */
    public HttpResponse test(HttpRequest request, ExecutionContext context) {
        return new SampleErrorResponseBuilder().build(request, context, null);
    }

    public class SampleErrorResponseBuilder extends ErrorResponseBuilder {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public HttpResponse build(final HttpRequest request,
                                  final ExecutionContext context, final Throwable throwable) {
            final HttpResponse response = new HttpResponse(404);
            response.setContentType(MediaType.APPLICATION_JSON);

            try {
                response.write(objectMapper.writeValueAsString("NotFound!!"));
            } catch (JsonProcessingException ignored) {
                return new HttpResponse(500);
            }
            return response;
        }
    }
}
