<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    table, th, td {
                    border: 1px solid black;
                    }

                    #wrapper {
                    width: 700px;
                    <!--border: 1px solid black;-->
                    position:absolute;
                    left:0; right:0;
                    top:0; bottom:0;
                    margin:auto;
                    }
                    #inner {
                    width: 300px;
                    float:left; /* add this */
                    border: 1px solid red;
                    }

                    h1 {
                    text-align: center;
                    }

                </style>
            </head>
            <body>
                <div id="wrapper">
                    <h1>Currency values</h1>

                    <xsl:for-each select="comparator/currencies">
                        <div id="inner">
                            <h2>Base currency : <xsl:value-of select="base"/></h2>
                            <h3>Evaluation date : <xsl:value-of select="date"/></h3>
                            <table>
                                <tr>
                                    <th>currency</th>
                                    <th>value</th>
                                </tr>
                                <tr>
                                    <td>MXN</td>
                                    <td><xsl:value-of select="/comparator/currencies/rates/currency[@type='MXN']"/></td>
                                </tr>
                                <tr>
                                    <td>LTL</td>
                                    <td><xsl:value-of select="/comparator/currencies/rates/currency[@type='LTL']"/></td>
                                </tr>
                                <tr>
                                    <td>ZAR</td>
                                    <td><xsl:value-of select="/comparator/currencies/rates/currency[@type='ZAR']"/></td>
                                </tr>
                                <tr>
                                    <td>INR</td>
                                    <td><xsl:value-of select="/comparator/currencies/rates/currency[@type='INR']"/></td>
                                </tr>
                                <tr>
                                    <td>CNY</td>
                                    <td><xsl:value-of select="/comparator/currencies/rates/currency[@type='CNY']"/></td>
                                </tr>
                                <tr>
                                    <td>MYR</td>
                                    <td><xsl:value-of select="/comparator/currencies/rates/currency[@type='MYR']"/></td>
                                </tr>
                                <tr>
                                    <td>USD</td>
                                    <td><xsl:value-of select="/comparator/currencies/rates/currency[@type='USD']"/></td>
                                </tr>
                            </table>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
