/*
 * This file is part of Haveno.
 *
 * Haveno is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Haveno is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Haveno. If not, see <http://www.gnu.org/licenses/>.
 */

package haveno.price.spot.providers;

import haveno.price.spot.ExchangeRate;
import haveno.price.spot.ExchangeRateProvider;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.Duration;

import java.util.HashSet;
import java.util.Set;

/**
 * Stub implementation (similar to #CoinMarketCap) for backward compatibility with legacy
 * Haveno clients
 */
@Component
class BitcoinAverage extends ExchangeRateProvider {

    public BitcoinAverage(Environment env) {
        // Simulate a deactivated BitcoinAverage provider
        // We still need the class to exist and be registered as a provider though,
        // because the returned data structure must contain the "btcAverageTs" key
        // for backward compatibility with Haveno clients which hardcode that key
        super(env, "BA", "btcAverage", Duration.ofMinutes(100));
    }

    /**
     * @see CoinMarketCap#doGet()
     * @return
     */
    @Override
    public Set<ExchangeRate> doGet() {

        HashSet<ExchangeRate> exchangeRates = new HashSet<>();
        exchangeRates.add(new ExchangeRate("NON_EXISTING_BASE_SYMBOL_BA", "NON_EXISTING_COUNTER_SYMBOL_BA", 0, 0L, getName()));
        return exchangeRates;
    }
}
