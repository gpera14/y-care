package com.framework.core.component;

import com.framework.core.execution.FeatureExecutioner;
import com.framework.core.factory.ComponentFactory;
import com.framework.core.global.Context;
import com.framework.core.helpers.ThreadLocalHelper;
import com.framework.core.managers.DriverManager;
import com.framework.core.factory.IDriver;
import com.framework.core.managers.TestCaseData;
import com.framework.core.models.Feature;
import com.framework.core.results.FeatureResult;
import com.framework.core.results.ScenarioResult;
import com.framework.core.results.StepResult;
import com.framework.utils.PathUtils;
import io.cucumber.gherkin.Gherkin;
import io.cucumber.messages.IdGenerator;
import io.cucumber.messages.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class ComplexComponent extends Component{

    private FeatureResult featureResult;

    @Override
    public void load(){

        IDriver componentDriver = ComponentFactory.loadProcess(componentType, config);

        DriverManager.setDriver(componentDriver);
    }

    @Override
    public void execute() {

        FeatureExecutioner featureExecutioner = null;

        for(Feature feature : featureList){

            parseFeature(feature.getName());

            TestCaseData.setTestCaseData(feature.getTestCaseData());

            featureExecutioner = new FeatureExecutioner(feature.getName(), componentType);

            featureExecutioner.execute();

            updateResult();
        }
    }

    @Override
    public void close() {

        DriverManager.componentDriver.get().Close();
    }

    private void parseFeature(String featureName){

        List<StepResult> stepList;

        ScenarioResult scenarioResult;

        StepResult stepResult;

        String path = PathUtils.featuresFolder + "/" + featureName;

        featureResult = new FeatureResult();

        List<ScenarioResult> scenarioList = new ArrayList<>();

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

            stepList = new ArrayList<>();

            scenarioResult = new ScenarioResult();

            List<Messages.GherkinDocument.Feature.Step> stepsList = scenario.getScenario().getStepsList();

            for(Messages.GherkinDocument.Feature.Step step : stepsList){

                stepResult = new StepResult();

                stepResult.setStepId(UUID.randomUUID());

                stepResult.setName(step.getText());

                stepResult.setExpectedResult(step.getDocString().getContent());

                stepList.add(stepResult);
            }

            scenarioResult.setName(scenario.getScenario().getName());

            scenarioResult.setResults(stepList);

            scenarioList.add(scenarioResult);
        }

        featureResult.setName(feature.getName());

        featureResult.setResult(scenarioList);

        ThreadLocalHelper.featureResult.set(featureResult);
    }

    private void updateResult(){


    }
}
