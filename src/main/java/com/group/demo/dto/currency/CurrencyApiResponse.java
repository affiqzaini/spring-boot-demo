package com.group.demo.dto.currency;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyApiResponse {

    private Map<String, CurrencyInfo> data;

    // {
    // "data": {
    // "CAD": {
    // "symbol": "CA$",
    // "name": "Canadian Dollar",
    // "symbol_native": "$",
    // "decimal_digits": 2,
    // "rounding": 0,
    // "code": "CAD",
    // "name_plural": "Canadian dollars",
    // "type": "fiat",
    // "countries": [
    // "CA"
    // ]
    // },
    // "EUR": {
    // "symbol": "€",
    // "name": "Euro",
    // "symbol_native": "€",
    // "decimal_digits": 2,
    // "rounding": 0,
    // "code": "EUR",
    // "name_plural": "Euros",
    // "type": "fiat",
    // "countries": [
    // "AD",
    // "AT",
    // "AX",
    // "BE",
    // "BL",
    // "CP",
    // "CY",
    // "DE",
    // "EA",
    // "EE",
    // "ES",
    // "EU",
    // "FI",
    // "FR",
    // "FX",
    // "GF",
    // "GP",
    // "GR",
    // "IC",
    // "IE",
    // "IT",
    // "LT",
    // "LU",
    // "LV",
    // "MC",
    // "ME",
    // "MF",
    // "MQ",
    // "MT",
    // "NL",
    // "PM",
    // "PT",
    // "RE",
    // "SI",
    // "SK",
    // "SM",
    // "TF",
    // "VA",
    // "XK",
    // "YT",
    // "ZW"
    // ]
    // },
    // "USD": {
    // "symbol": "$",
    // "name": "US Dollar",
    // "symbol_native": "$",
    // "decimal_digits": 2,
    // "rounding": 0,
    // "code": "USD",
    // "name_plural": "US dollars",
    // "type": "fiat",
    // "countries": [
    // "AC",
    // "AS",
    // "BQ",
    // "DG",
    // "EC",
    // "FM",
    // "GU",
    // "HT",
    // "IO",
    // "MH",
    // "MP",
    // "PA",
    // "PR",
    // "PW",
    // "SV",
    // "TC",
    // "TL",
    // "UM",
    // "US",
    // "VG",
    // "VI",
    // "ZW"
    // ]
    // }
    // }
    // }

}
