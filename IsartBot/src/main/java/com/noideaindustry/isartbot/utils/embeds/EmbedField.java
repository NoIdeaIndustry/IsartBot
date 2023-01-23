package com.noideaindustry.isartbot.utils.embeds;

public class EmbedField {
    final String primaryValue;
    final String secondValue;
    final boolean sameLine;

    public EmbedField(final String primaryValue, final String secondValue, final boolean sameLine) {
        this.primaryValue = primaryValue;
        this.secondValue = secondValue;
        this.sameLine = sameLine;
    }

    public EmbedField(final String primaryValue, final String secondValue) {
        this.primaryValue = primaryValue;
        this.secondValue = secondValue;
        this.sameLine = false;
    }

    public EmbedField(final String primaryValue, final boolean sameLine) {
        this.primaryValue = primaryValue;
        this.secondValue = "";
        this.sameLine = sameLine;
    }

    public String getPrimaryValue() {
        return primaryValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public boolean isSameLine() {
        return sameLine;
    }
}
