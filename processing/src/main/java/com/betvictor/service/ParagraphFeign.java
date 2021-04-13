package com.betvictor.service;

import com.betvictor.config.FeignConfig;
import com.betvictor.model.RandomTextResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "randomText", url = "http://www.randomtext.me/api/giberish/", configuration = FeignConfig.class)
public interface ParagraphFeign {

    @RequestMapping(method = RequestMethod.GET,
            value = "p-{numberOfParagraphsRequest}/{minNumberOfWordsPerSentence}-{maxNumberOfWordsPerSentence}",
            produces = "application/json")
    RandomTextResponse getRandomText(@PathVariable int numberOfParagraphsRequest,
                                     @PathVariable int minNumberOfWordsPerSentence,
                                     @PathVariable int maxNumberOfWordsPerSentence);
}
