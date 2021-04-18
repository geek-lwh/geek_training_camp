package week_05.homework1.log.impl;

import lombok.Data;
import week_05.homework1.aop.ILog;

@Data
public class ApplicationLog implements ILog {

    @Override
    public void saveLog() {
        System.out.println("app save log");
    }

}
