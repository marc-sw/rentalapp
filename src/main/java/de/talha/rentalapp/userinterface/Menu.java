package de.talha.rentalapp.userinterface;

import de.talha.rentalapp.abstraction.Displayable;
import de.talha.rentalapp.abstraction.SimpleDisplayable;

import java.util.List;

public class Menu implements Displayable, SimpleDisplayable {

    private List<Option> options;

    public Menu(List<Option> options) {
        this.options = options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Option> getOptions() {
        return options;
    }

    public String display() {
        StringBuilder builder = new StringBuilder();
        builder.append("---------------------------").append(System.lineSeparator());
        for (Option option: options) {
            builder.append("%s: %s".formatted(option.getName(), option.getDescription())).append(System.lineSeparator());
        }
        builder.append(("---------------------------"));
        return builder.toString();
    }

    @Override
    public String displaySimple() {
        StringBuilder builder = new StringBuilder();
        builder.append("---------------------------").append(System.lineSeparator());
        int i = 1;
        for (Option option: options) {
            builder.append("%d %s".formatted(i++, option.getName())).append(System.lineSeparator());
        }
        builder.append(("---------------------------"));
        return builder.toString();
    }
}
