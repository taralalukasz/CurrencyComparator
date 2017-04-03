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
                                    <th>abbreviation</th>
                                </tr>
                                <tr>
                                    <td>AUD</td>
                                    <td><xsl:value-of select="rates/AUD"/></td>
                                    <td>AUD</td>
                                </tr>
                                <tr>
                                    <td>CHF</td>
                                    <td><xsl:value-of select="rates/CHF"/></td>
                                    <td>CHF</td>
                                </tr>
                                <tr>
                                    <td>EUR</td>
                                    <td><xsl:value-of select="rates/EUR"/></td>
                                    <td>EUR</td>
                                </tr>
                            </table>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
