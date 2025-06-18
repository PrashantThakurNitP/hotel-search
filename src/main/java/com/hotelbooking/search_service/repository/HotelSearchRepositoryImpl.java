package com.hotelbooking.search_service.repository;


import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.hotelbooking.search_service.entity.HotelSearchDocument;
import com.hotelbooking.search_service.entity.HotelSearchFilter;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchRepositoryImpl implements HotelSearchCustomRepository {


    private final ElasticsearchTemplate elasticsearchTemplate;

    public HotelSearchRepositoryImpl(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public List<HotelSearchDocument> searchByCityNameAndMinRating(HotelSearchFilter hotelSearchFilter) {

        BoolQuery.Builder boolQueryBuilder = new BoolQuery.Builder();

        if (hotelSearchFilter.getCity() != null && !hotelSearchFilter.getCity().isBlank()) {
            Query cityQuery = MatchQuery.of(m -> m
                    .field("city")
                    .query(hotelSearchFilter.getCity())
            )._toQuery();
            boolQueryBuilder.must(cityQuery);
        }

        if (hotelSearchFilter.getName() != null && !hotelSearchFilter.getName().isBlank()) {
            Query nameQuery = MatchQuery.of(m -> m
                    .field("name")
                    .query(hotelSearchFilter.getName())
            )._toQuery();
            boolQueryBuilder.must(nameQuery);
        }


        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q.bool(boolQueryBuilder.build()))
                .build();

        SearchHits<HotelSearchDocument> hits =
                elasticsearchTemplate.search(query, HotelSearchDocument.class);

        return hits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
