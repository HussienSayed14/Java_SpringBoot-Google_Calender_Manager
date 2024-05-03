package com.ropulva.CalendarManagement.creator;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatorService {

    private final CreatorRepository creatorRepository;
    private static final Logger logger = LoggerFactory.getLogger(CreatorService.class);


    public CreatorModel createAndGetCreator(String email, String name){
        try{
            CreatorModel existingCreator = creatorRepository.getCreator(email);
            if(existingCreator != null) return existingCreator;

            CreatorModel newCreator = CreatorModel.builder()
                    .email(email)
                    .name(name)
                    .build();
            creatorRepository.save(newCreator);
            return newCreator;

        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

    }
}
