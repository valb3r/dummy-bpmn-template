package ua.gdnext.controller;

import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class DummyController {

    private final RuntimeService runtimeService;
    private final HistoryService historyService;

    public DummyController(RuntimeService runtimeService, HistoryService historyService) {
        this.runtimeService = runtimeService;
        this.historyService = historyService;
    }

    @GetMapping("/required-params")
    public Map<String, String> getRequiredParams() {
        var pi = runtimeService.startProcessInstanceByKey("dummy-process");
        var requiredPrams = (String) pi.getProcessVariables().get("required"); // A,B
        var data = new HashMap<String, String>();
        data.putAll(Arrays.stream(requiredPrams.split(",")).collect(Collectors.toMap(it -> it, it -> "true")));
        data.put("processInstanceId", pi.getId());
        return data;
    }

    @PostMapping("/required-params/{processInstanceId}")
    public Map<String, Integer> updateRequiredParams(@PathVariable String processInstanceId, @RequestBody Map<String, Integer> params) {
        var execution = runtimeService.createExecutionQuery()
                .processInstanceId(processInstanceId)
                .messageEventSubscriptionName("userProvidedAB")
                .singleResult();

        runtimeService.trigger(
                execution.getId(),
                null,
                (Map) params
        );

        var result = (Integer) historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).variableName("result").singleResult().getValue();

        return Map.of(
                "result",
                result
        );
    }
}
