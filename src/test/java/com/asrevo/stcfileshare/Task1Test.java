package com.asrevo.stcfileshare;

import org.junit.jupiter.api.Test;

import static com.asrevo.stcfileshare.Task1.solution;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class Task1Test {
    @Test
    public void expectCase1() {
        assertThat(solution("abd(jnb)asdf"), equalTo("abd(bnj)asdf"));
    }

    @Test
    public void expectCase2() {
        assertThat(solution("abdjnbasdf"), equalTo("abdjnbasdf"));
    }

    @Test
    public void expectCase3() {
        assertThat(solution("dd(df)a(ghhh)"), equalTo("dd(fd)a(hhhg)"));
    }
}
