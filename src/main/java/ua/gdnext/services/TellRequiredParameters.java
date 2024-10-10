package ua.gdnext.services;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class TellRequiredParameters implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        // Tell A,B required
        execution.setVariable("required", "A,B");
    }
}
