package com.webauthn4j.request;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.webauthn4j.registry.Registry;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class AttestationConveyancePreferenceTest {

    Registry registry = new Registry();

    @Test
    public void create_test() {
        assertThat(AttestationConveyancePreference.create("none")).isEqualTo(AttestationConveyancePreference.NONE);
        assertThat(AttestationConveyancePreference.create("direct")).isEqualTo(AttestationConveyancePreference.DIRECT);
        assertThat(AttestationConveyancePreference.create("indirect")).isEqualTo(AttestationConveyancePreference.INDIRECT);
    }

    @Test
    public void create_test_with_null_value() {
        assertThat(AttestationConveyancePreference.create(null)).isEqualTo(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_test_with_invalid_value() {
        AttestationConveyancePreference.create("invalid");
    }

    @Test
    public void getValue_test() {
        assertThat(AttestationConveyancePreference.NONE.getValue()).isEqualTo("none");
        assertThat(AttestationConveyancePreference.DIRECT.getValue()).isEqualTo("direct");
        assertThat(AttestationConveyancePreference.INDIRECT.getValue()).isEqualTo("indirect");
    }

    @Test
    public void fromString_test() throws IOException {
        TestDTO dto = registry.getJsonMapper().readValue("{\"preference\":\"none\"}", TestDTO.class);
        assertThat(dto.preference).isEqualTo(AttestationConveyancePreference.NONE);
    }

    @Test(expected = InvalidFormatException.class)
    public void fromString_test_with_invalid_value() throws IOException {
        registry.getJsonMapper().readValue("{\"preference\":\"invalid\"}", TestDTO.class);
    }


    public static class TestDTO{
        public AttestationConveyancePreference preference;
    }
}