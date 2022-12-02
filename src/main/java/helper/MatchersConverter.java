package helper;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class MatchersConverter {
    public static Matcher getMatcher(String matcherStr, String value) {
        Matcher matcher = null;
        switch (matcherStr) {
            case "equals":
                matcher = Matchers.equalTo(value);
                break;
            case "arrayWithSize":
                matcher = Matchers.hasSize(Integer.parseInt(value));
                break;
            case "equalsBoolean":
                matcher = Matchers.equalTo(Boolean.parseBoolean(value));
                break;
            case "equalsNumber":
                matcher = Matchers.equalTo(Float.parseFloat(value));
                break;
            case "equalsInt":
                matcher = Matchers.equalTo(Integer.parseInt(value));
                break;
            case "contains":
                matcher = Matchers.containsString(value);
                break;
            case "hasEntry":
                matcher = Matchers.hasItem(value);
                break;
            case "isNotNull":
                matcher = Matchers.notNullValue();
                break;
            case "isNull":
                matcher = Matchers.nullValue();
                break;
            case "containsInAnyOrder":
                String[] array = value.split(",");
                matcher = Matchers.containsInAnyOrder(array);
                break;
            case "isTrueOrFalse":
                matcher = Matchers.anyOf(Matchers.equalTo(true), Matchers.equalTo(false));
                break;
            case "isOneOf":
                String[] list = value.split(",");
                matcher = Matchers.is(Matchers.oneOf(list));
                break;
            case "objectSizeIs":
                matcher = Matchers.aMapWithSize(Integer.parseInt(value));
                break;
            case "doesNotEqual":
                matcher = Matchers.not(Matchers.equalTo(value));
                break;
            case "checkValues" :
                matcher= either(equalToIgnoringCase(value)).or(equalToIgnoringCase(value)).or(equalToIgnoringCase(value));
            default:
                Assert.fail("Error: Wrong matcher string passed.");
        }

        return (Matcher)matcher;
    }

    private MatchersConverter() {
        throw new IllegalStateException("Utility class");
    }
}
