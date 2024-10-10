package ua.gdnext.services;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class ComputeAplusB implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        // A + B
        var a = (int) execution.getTransientVariable("A");
        var b = (int) execution.getTransientVariable("B");

        execution.setTransientVariable("result", a + b);
    }
}
