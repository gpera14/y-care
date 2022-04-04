package com.framework.core.reader;

import com.framework.core.models.Feature;
import io.cucumber.gherkin.Gherkin;
import io.cucumber.messages.IdGenerator;
import io.cucumber.messages.Messages;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class FeatureFileReader {

    public static void parseFeature(String path){

//        List<ScenarioStep> stepList;
//
//        FeatureScenario featureScenario;
//
//        ScenarioStep scenarioStep;

        Feature lFeature = new Feature();

//        List<FeatureScenario> scenarioList = new ArrayList<>();

        //uri = "/Users/saadalikhan/eclipse-workspace/Digital-Automation-Mobile/src/test/resources/Mobile/gg_features/gg_dashboard.feature";

        IdGenerator idGenerator = new IdGenerator.Incrementing();

        List<String> paths = singletonList(path);

        boolean includeSource = false;

        boolean includeAst = true;

        boolean includePickles = false;

        List<Messages.Envelope> envelopes = Gherkin.fromPaths(paths, includeSource, includeAst, includePickles, idGenerator).collect(Collectors.toList());

        // Get the AST
        Messages.GherkinDocument gherkinDocument = envelopes.get(0).getGherkinDocument();

        // Get the Feature node of the AST
        Messages.GherkinDocument.Feature feature = gherkinDocument.getFeature();

        // Get the first Scenario node of the Feature node
        List<Messages.GherkinDocument.Feature.FeatureChild> featureChildList = feature.getChildrenList();

        for(Messages.GherkinDocument.Feature.FeatureChild scenario : featureChildList){

//            stepList = new ArrayList<>();
//
//            featureScenario = new FeatureScenario();

            List<Messages.GherkinDocument.Feature.Step> stepsList = scenario.getScenario().getStepsList();

            for(Messages.GherkinDocument.Feature.Step step : stepsList){

//                scenarioStep = new ScenarioStep();
//
//                scenarioStep.setName(step.getText());
//
//                scenarioStep.setExpectedResult(step.getDocString().getContent());
//
//                stepList.add(scenarioStep);
            }

//            featureScenario.setName(scenario.getScenario().getName());
//
//            featureScenario.setStepList(stepList);
//
//            scenarioList.add(featureScenario);
        }

        lFeature.setName(feature.getName());

//        lFeature.setScenarioList(scenarioList);
//
//        ThreadLocalManager.feature.set(lFeature);
    }
}
