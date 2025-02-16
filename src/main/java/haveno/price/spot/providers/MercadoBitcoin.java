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

import org.knowm.xchange.mercadobitcoin.MercadoBitcoinExchange;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.Duration;

import java.util.Set;

@Component
class MercadoBitcoin extends ExchangeRateProvider {

    public MercadoBitcoin(Environment env) {
        super(env, "MercadoBitcoin", "mercadobitcoin", Duration.ofMinutes(1));
    }

    @Override
    public Set<ExchangeRate> doGet() {
        // Supported fiat: BRL (Brazilian Real)
        // Supported alts: -
        return doGet(MercadoBitcoinExchange.class);
    }

}
