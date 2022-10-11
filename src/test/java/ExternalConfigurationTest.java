import org.junit.jupiter.api.Test;

import com.org.promotion.configs.PromotionConfigProperties;
import com.org.promotion.configs.PromotionExternalConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExternalConfigurationTest {
    private static final PromotionConfigProperties configurationProperties =
            PromotionExternalConfiguration.getInstance().getConfigurationProperties();

    @Test
    public void testConfig() {
        assertFalse(configurationProperties.getProducts().isEmpty());
    }
}
