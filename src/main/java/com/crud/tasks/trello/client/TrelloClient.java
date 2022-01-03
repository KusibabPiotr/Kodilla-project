package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TrelloClient {

    private final TrelloConfig config;
    private final RestTemplate restTemplate;


    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = buildUrl();

        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
            return Optional.ofNullable(boardsResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(p -> Objects.nonNull(p.getId()) && Objects.nonNull(p.getName()))
                    .collect(Collectors.toList());
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return Collections.emptyList();
        }

    }

    public CreatedTrelloCard createNewCard(final TrelloCardDto card) {

        URI url = UriComponentsBuilder.fromHttpUrl(config.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", config.getTrelloAppKey())
                .queryParam("token", config.getTrelloToken())
                .queryParam("name",card.getName())
                .queryParam("desc",card.getDescription())
                .queryParam("pos",card.getPos())
                .queryParam("idList",card.getIdList())
                .build()
                .encode()
                .toUri();
        return restTemplate.postForObject(url,null, CreatedTrelloCard.class);
    }

    private URI buildUrl() {
        return UriComponentsBuilder.fromHttpUrl(config.getTrelloApiEndpoint() + "/members/" + config.getTrelloUser() + "/boards")
                .queryParam("key", config.getTrelloAppKey())
                .queryParam("token", config.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists","all")
                .build()
                .encode()
                .toUri();
    }
}
