package org.jhouse.survey.vo;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Observable;
import java.util.Set;

/**
 * Created by jhouse on 12/14/14.
 */
public class QuizVo extends Observable {
    private  Set<ElectVo> result = Collections.synchronizedSet(new LinkedHashSet<ElectVo>());
    private int number;
    private String description;

    public QuizVo() {
    }

    public QuizVo(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addResult(ElectVo electVo) {
        result.add(electVo);
    }

    public Set<ElectVo> getResult() {
        return result;
    }


}
