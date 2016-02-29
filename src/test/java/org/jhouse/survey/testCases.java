package org.jhouse.survey;

import org.jhouse.survey.vo.RankVo;
import org.junit.Test;

/**
 * Created by jhouse on 12/17/14.
 */
public class testCases {

    @Test
    public void TestRANKEnum() throws Exception{

        System.out.println(RankVo.RANK.FIRST_CLASS.toString());
        System.out.println(RankVo.RANK.valueOf("FIRST_CLASS"));
        System.out.println(RankVo.RANK.FIRST_CLASS.getIntValue());

    }
}
