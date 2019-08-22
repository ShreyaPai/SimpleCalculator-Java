package main;

public class SanitizeInput {

    public String cleanInput(String input) {
        String specialCharactersRemoved = input.replaceAll("[^0-9+-?*?/()=]", "");
        String addedSpaceToPlus = specialCharactersRemoved.replaceAll("[+]", " + ");
        String addedSpaceToMinus = addedSpaceToPlus.replaceAll("[-]", " - ");
        String addedSpaceToStar = addedSpaceToMinus.replaceAll("[?*]", " * ");
        String addedSpaceToOpenBrace = addedSpaceToStar.replaceAll("[(]", " ( ");
        String addedSpaceToClosingBrace = addedSpaceToOpenBrace.replaceAll("[)]", " ) ");
        String finalString = addedSpaceToClosingBrace.replaceAll("[?/]", " / ");
        return finalString;
    }

}
