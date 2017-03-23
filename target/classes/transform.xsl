<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    table, th, td {
                    border: 1px solid black;
                    }
                </style>
            </head>
            <body>
                <h1>Currency values</h1>
                <h2>Base currency : <xsl:value-of select="currency/base"/></h2>
                <h3>Evaluation date : <xsl:value-of select="currency/date"/></h3>

                <table>
                    <tr>
                        <th>currency</th>
                        <th>value</th>
                        <th>abbreviation</th>
                    </tr>
                    <tr>
                        <td>Euro</td>
                        <td><xsl:value-of select="currency/rates/EUR"/></td>
                        <td>EUR</td>
                    </tr>
                    <tr>
                        <td>Swiss Frank</td>
                        <td><xsl:value-of select="currency/rates/CHF"/></td>
                        <td>CHF</td>
                    </tr>
                    <tr>
                        <td>Australina Dollar</td>
                        <td><xsl:value-of select="currency/rates/AUD"/></td>
                        <td>AUD</td>
                    </tr>
                    <tr>
                        <td>Japanese Yen</td>
                        <td><xsl:value-of select="currency/rates/JPY"/></td>
                        <td>JPY</td>
                    </tr>
                    <tr>
                        <td>Polish Zloty</td>
                        <td><xsl:value-of select="currency/rates/PLN"/></td>
                        <td>PLN</td>
                    </tr>
                    <tr>
                        <td>British Pound</td>
                        <td><xsl:value-of select="currency/rates/GBP"/></td>
                        <td>GBP</td>
                    </tr>
                    <tr>
                        <td>Hungarian Forint</td>
                        <td><xsl:value-of select="currency/rates/HUF"/></td>
                        <td>HUF</td>
                    </tr>
                    <tr>
                        <td>Czech Crown</td>
                        <td><xsl:value-of select="currency/rates/CZK"/></td>
                        <td>CZK</td>
                    </tr>
                    <tr>
                        <td>Swedish Crown</td>
                        <td><xsl:value-of select="currency/rates/SEK"/></td>
                        <td>SEK</td>
                    </tr>
                </table>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
