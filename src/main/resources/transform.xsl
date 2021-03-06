<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="utf-8"/>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
                    float:left;
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
                            <h5>Evaluation date : <xsl:value-of select="date"/></h5>
                            <table class="table table-striped">
                                <tr>
                                    <th>currency</th>
                                    <th>value</th>
                                </tr>
                                <tr>
                                    <td>AUD</td>
                                    <td><xsl:value-of select="/comparator/currencies/rates/currency[@type='AUD']"/></td>
                                </tr>
                                <tr>
                                    <td>USD</td>
                                    <td><xsl:value-of select="/comparator/currencies/rates/currency[@type='USD']"/></td>
                                </tr>
                                <tr>
                                    <td>CZK</td>
                                    <td><xsl:value-of select="/comparator/currencies/rates/currency[@type='CZK']"/></td>
                                </tr>
                                <tr>
                                    <td>NZD</td>
                                    <td><xsl:value-of select="/comparator/currencies/rates/currency[@type='NZD']"/></td>
                                </tr>

                            </table>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
