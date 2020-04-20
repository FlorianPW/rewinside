package net.quatulo.lobby.utilities;
public class PrefixBuilder {

    String prefix;

    public PrefixBuilder(String prefix) {
        this.prefix = prefix;
    }

    public String build() {
        return " §8┃ " + prefix + " §8➜ §7";
    }

}