package avachat.learn;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by avachat on 11/13/15.
 */
public class StreamShortcuts {

    public class SomePOJO {

        private String str = "test";

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }

    public static class SomePredicate implements Predicate<SomePOJO> {

        @Override
        public boolean test(SomePOJO somePOJO) {
            return (null == somePOJO.getStr());
        }
    }


    public static class SomeConverter implements Function<SomePOJO, Integer> {

        @Override
        public Integer apply(SomePOJO somePOJO) {
            return somePOJO.getStr().length();
        }
    }


    public static class ConditionalConverter {

        private final Predicate<SomePOJO> predicate;
        private final Function<SomePOJO, Integer> converter;

        public ConditionalConverter(Predicate<SomePOJO> predicate, Function<SomePOJO, Integer> converter) {
            this.predicate = predicate;
            this.converter = converter;
        }

        public Predicate<SomePOJO> getPredicate() {
            return predicate;
        }

        public Function<SomePOJO, Integer> getConverter() {
            return converter;
        }
    }

    public static class SpecificPredicate implements Predicate<ConditionalConverter> {

        private final SomePOJO somePOJO;

        public SpecificPredicate(SomePOJO somePOJO) {
            this.somePOJO = somePOJO;
        }

        @Override
        public boolean test(ConditionalConverter conditionalConverter) {
            return conditionalConverter.getPredicate().test(somePOJO);
        }
    }

    public StreamShortcuts() {

    }

    public List<Integer> vectorConversion_1(SomePOJO somePOJO, List<ConditionalConverter> conditionalConverterList) {
        List<Integer> integerList = new ArrayList<>();

        //conditionalConverterList.stream().filter(p -> p.getPredicate().test(somePOJO)).collect(Collectors.toList());

        SpecificPredicate specificPredicate = new SpecificPredicate(somePOJO);
        Predicate<ConditionalConverter> predicate1 = (ConditionalConverter p) -> {
            return p.getPredicate().test(somePOJO);
        };
        Predicate<ConditionalConverter> predicate2 = p -> {
            return p.getPredicate().test(somePOJO);
        };
        Predicate<ConditionalConverter> predicate = p -> {
            return p.getPredicate().test(somePOJO);
        };
        conditionalConverterList.stream().filter(predicate).count();

        return integerList;
    }

    public List<Integer> vectorConversion_2(SomePOJO somePOJO, List<Predicate<SomePOJO>> predicateList) {
        List<Integer> integerList = new ArrayList<>();

        predicateList.stream().filter(p -> p.test(somePOJO)).count();

        return integerList;
    }

}
