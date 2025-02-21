package ru.project.shift.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.project.shift.model.Data;

import static org.assertj.core.api.Assertions.*;

class DataFilterTest {
    private DataFilter dataFilter;
    private Data data;

    @BeforeEach
    void setUp() {
        dataFilter = new DataFilter();
        data = new Data();
    }

    @Test
    void whenFilterIntegerThenSuccessful() {
        String line1 = "12345";
        String line2 = "-12345";
        dataFilter.filter(data, line1);
        dataFilter.filter(data, line2);

        assertThat(data.getIntegers().size()).isEqualTo(2);
        assertThat(data.getIntegers().get(0)).isEqualTo(12345L);
        assertThat(data.getIntegers().get(1)).isEqualTo(-12345L);
        assertThat(data.getDoubles()).isEmpty();
        assertThat(data.getStrings()).isEmpty();
    }

    @Test
    void whenFilterDoublesThenSuccessful() {
        String line1 = "10.5";
        String line2 = "1.23E-4";
        dataFilter.filter(data, line1);
        dataFilter.filter(data, line2);

        assertThat(data.getDoubles().size()).isEqualTo(2);
        assertThat(data.getDoubles().get(0)).isEqualTo(10.5);
        assertThat(data.getDoubles().get(1)).isEqualTo(1.23E-4);
        assertThat(data.getStrings()).isEmpty();
        assertThat(data.getIntegers()).isEmpty();
    }

    @Test
    void whenFilterStringsThenSuccessful() {
        String line1 = "String";
        String line2 = "I Love Java";
        dataFilter.filter(data, line1);
        dataFilter.filter(data, line2);

        assertThat(data.getStrings().size()).isEqualTo(2);
        assertThat(data.getStrings().get(0)).isEqualTo("String");
        assertThat(data.getStrings().get(1)).isEqualTo("I Love Java");
        assertThat(data.getIntegers()).isEmpty();
        assertThat(data.getDoubles()).isEmpty();
    }

    @Test
    void whenFilterNotValidDataThenNothingHappens() {
        String line1 = "@#*";
        String line2 = "_+ &";
        dataFilter.filter(data, line1);
        dataFilter.filter(data, line2);
        assertThat(data.getDoubles()).isEmpty();
        assertThat(data.getStrings()).isEmpty();
        assertThat(data.getIntegers()).isEmpty();
    }
}