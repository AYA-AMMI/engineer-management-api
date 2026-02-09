package com.tuts.springboot;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineerService {

    private final SoftwareEngineerRepository softwareEngineerRepository;

    private final AiService aiService;

    public SoftwareEngineerService(SoftwareEngineerRepository softwareEngineerRepository, AiService aiService) {
        this.softwareEngineerRepository = softwareEngineerRepository;
        this.aiService = aiService;
    }

    public List<SoftwareEngineer> getAllSoftwareEngineers(){
        return softwareEngineerRepository.findAll();
    }

    public void insertSoftwareEngineer(
            SoftwareEngineer softwareEngineer
    ) {
        String prompt = """
                Based on the programming tech stack %s that %s
                has given Provide a full learning path and 
                recommendations for this person.
                """.formatted(softwareEngineer.getTechStack(),
                softwareEngineer.getName()) ;

        String chatRs = aiService.chat(prompt);
        softwareEngineer.setLearningPathRecommendation(chatRs);
        softwareEngineerRepository.save(softwareEngineer);
    }


    public SoftwareEngineer getSoftwareEngineerById(Integer id) {
        return softwareEngineerRepository.findById(id).orElseThrow(() -> new IllegalStateException(id+ " not found"));
    }

    public void deleteSoftwareEngineer(Integer id) {
        softwareEngineerRepository.deleteById(id);
    }


    public void updateSoftwareEngineer(Integer id, SoftwareEngineer update) {
        SoftwareEngineer softwareEngineer1 = softwareEngineerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        id + " not found"));
        softwareEngineer1.setName(update.getName());
        softwareEngineer1.setTechStack(update.getTechStack());
        softwareEngineerRepository.save(softwareEngineer1);
    }
}
