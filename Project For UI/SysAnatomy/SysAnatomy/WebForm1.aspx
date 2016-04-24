<!DOCTYPE HTML>
<!--
	This is the HomePage for SysAnatomy
-->
<html>
<head>
    <title>SysAnatomy Home</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />


    <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="assets/css/main.css" />
    <!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->

    <!-- Scripts -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/jquery.dropotron.min.js"></script>
    <script src="assets/js/skel.min.js"></script>
    <script src="assets/js/skel-viewport.min.js"></script>
    <script src="assets/js/util.js"></script>
    <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
    <script src="assets/js/main.js"></script>
    <script type="text/javascript" src="amcharts/amcharts.js"></script>
    <script type="text/javascript" src="amcharts/serial.js"></script>
    <script type="text/javascript" src="https://www.amcharts.com/lib/3/pie.js"></script>
    <script type="text/javascript" src="https://www.amcharts.com/lib/3/gauge.js"></script>
    <script type="text/javascript" src="https://www.amcharts.com/lib/3/themes/light.js"></script>
    <script type="text/javascript" src="https://www.amcharts.com/lib/3/themes/dark.js"></script>

</head>
<body class="homepage">
    <div id="page-wrapper">

        <!-- Header -->
        <div id="header-wrapper">
            <div id="header" class="container">

                <!-- Logo -->
                <h1 id="logo"><a href="index.html">Sys-Anatomy</a></h1>
                <p>A reporting tool that helps you know your System better.</p>

                <!-- Nav -->
                <nav id="nav">
                    <ul>
                        <li><a class="icon fa-home" href="WebForm1.aspx"><span>Go Home</span></a></li>
                        <li><a class="icon fa-cog" href="#"><span>About your PC</span></a></li>

                        <li>
                            <a href="#" class="icon fa-bar-chart-o"><span>Process details</span></a>
                            <ul>
                                <li><a href="#">Process 1</a></li>
                                <li><a href="#">Process 2</a></li>
                                <li><a href="#">Process 3</a></li>
                                <%--<li>
                                    <a href="#">Process 4</a>
                                    <ul>
                                        <li><a href="#">Magna phasellus</a></li>
                                        <li><a href="#">Etiam dolore nisl</a></li>
                                        <li><a href="#">Phasellus consequat</a></li>
                                    </ul>
                                </li>
                                <li><a href="#">Veroeros feugiat</a></li>--%>
                            </ul>
                        </li>
                        <li><a class="icon fa-retweet" href="#"><span>CPU Stats</span></a></li>
                        <li><a class="icon fa-sitemap" href="#"><span>Summarized Report</span></a></li>
                        <li><a class="icon fa-sitemap" href="#"><span>File System</span></a></li>
                    </ul>
                </nav>

            </div>
        </div>

        <!--Button-->
        <center>
        <div class="monitor windows" style="width: 50%">
            <a class="button" id="download-link" href="#">Download Desktop Service</a>
        </div>
        </center>
        <!-- Features -->
        <div id="features-wrapper">
            <section id="features" class="container">
                <header>
                    <h2>Gentlemen, behold! This is <strong>all your running processes on a HeatMap</strong>!</h2>
                </header>
                <div id="chartdiv" style="width: 100%; height: 400px;"></div>

                <div style="height: 10%; width: 100%;"></div>
                

                <div class="row">
                    <div class="4u 12u(mobile)">
                        <!-- Feature -->
                        <section>
                            <div id="chartdiv1" style="width: 100%; height: 400px;"></div>
                            <header>
                                <h3>This is the Memory graph</h3>
                            </header>
                        </section>
                    </div>
                    <div class="4u 12u(mobile)">
                        <!-- Feature -->
                        <section>
                           <div id="chartdiv2" style="width: 100%; height: 400px;"></div>
                            <header>
                                <h3>this is graph for no. of active processes</h3>
                            </header>
                        </section>

                    </div>
                    <div class="4u 12u(mobile)">
                        <!-- Feature -->
                        <section>
                           <div id="chartdiv3" style="width: 200%; height: 400px;"></div>
                            <header>
                                <h3>this is CPU clock speed live meter</h3>
                            </header>
                        </section>
                    </div>
                </div>
            </section>
        </div>

        <!-- Footer -->
        <div id="footer-wrapper">
            <div id="footer" class="container">
                <header>
                    <h2>Questions or comments? <strong>Get in touch:</strong></h2>
                </header>
                <div class="row">
                    <div class="6u 12u(mobile)">
                        <section>
                            <form method="post" action="#">
                                <div class="row 50%">
                                    <div class="6u 12u(mobile)">
                                        <input name="name" placeholder="Name" type="text" />
                                    </div>
                                    <div class="6u 12u(mobile)">
                                        <input name="email" placeholder="Email" type="text" />
                                    </div>
                                </div>
                                <div class="row 50%">
                                    <div class="12u">
                                        <textarea name="message" placeholder="Message"></textarea>
                                    </div>
                                </div>
                                <div class="row 50%">
                                    <div class="12u">
                                        <a href="#" class="form-button-submit button icon fa-envelope">Send Message</a>
                                    </div>
                                </div>
                            </form>
                        </section>
                    </div>
                    <div class="6u 12u(mobile)">
                        <section>
                            <p>
                                Got any improvements or suggestions? Feel free to connect and contact us here.
                                Your suggestions are always welcome.
                            </p>
                            <div class="row">
                                <div class="6u 12u(mobile)">
                                    <ul class="icons">
                                        <li class="icon fa-home">97 Albany Street<br />
                                            Poughkeepsie, NY 12601<br />
                                            USA
                                        </li>
                                        <li class="icon fa-phone">(914) 755-5228
                                        </li>
                                        <li class="icon fa-envelope">
                                            <a href="#">Sourav.Bhowmik1@marist.edu</a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="6u 12u(mobile)">
                                    <ul class="icons">
                                        <li class="icon fa-twitter">
                                            <a href="#">@souravTwitter-tld</a>
                                        </li>
                                        <li class="icon fa-instagram">
                                            <a href="#">instagram.com/sourav-tld</a>
                                        </li>
                                        <li class="icon fa-dribbble">
                                            <a href="#">dribbble.com/sourav-tld</a>
                                        </li>
                                        <li class="icon fa-facebook">
                                            <a href="#">facebook.com/Sourav-tld</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </div>

        </div>

    </!--div>
    <!-- fancy 3D pie-chart-->
    <script>
        var chart = AmCharts.makeChart("chartdiv3", {
            "type": "pie",
            "theme": "dark",
            "dataProvider": [{
                "country": "Lithuania",
                "value": 260
            }, {
                "country": "Ireland",
                "value": 201
            }, {
                "country": "Germany",
                "value": 65
            }, {
                "country": "Australia",
                "value": 39
            }, {
                "country": "UK",
                "value": 19
            }, {
                "country": "Latvia",
                "value": 10
            }],
            "valueField": "value",
            "titleField": "country",
            "outlineAlpha": 0.4,
            "depth3D": 15,
            "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>",
            "angle": 30,
            "export": {
                "enabled": true
            }
        });
        jQuery('.chart-input').off().on('input change', function () {
            var property = jQuery(this).data('property');
            var target = chart;
            var value = Number(this.value);
            chart.startDuration = 0;

            if (property == 'innerRadius') {
                value += "%";
            }

            target[property] = value;
            chart.validateNow();
        });
    </script>
    <!--wavy graph aka Area with time based info-->
    <script>
        var chartData = generateChartData();

        var chart = AmCharts.makeChart("chartdiv2", {
            "type": "serial",
            "theme": "dark",
            "marginRight": 80,
            "dataProvider": chartData,
            "valueAxes": [{
                "position": "left",
                "title": "Unique visitors"
            }],
            "graphs": [{
                "id": "g1",
                "fillAlphas": 0.4,
                "valueField": "visits",
                "balloonText": "<div style='margin:5px; font-size:19px;'>Visits:<b>[[value]]</b></div>"
            }],
            "chartScrollbar": {
                "graph": "g1",
                "scrollbarHeight": 80,
                "backgroundAlpha": 0,
                "selectedBackgroundAlpha": 0.1,
                "selectedBackgroundColor": "#888888",
                "graphFillAlpha": 0,
                "graphLineAlpha": 0.5,
                "selectedGraphFillAlpha": 0,
                "selectedGraphLineAlpha": 1,
                "autoGridCount": true,
                "color": "#AAAAAA"
            },
            "chartCursor": {
                "categoryBalloonDateFormat": "JJ:NN, DD MMMM",
                "cursorPosition": "mouse"
            },
            "categoryField": "date",
            "categoryAxis": {
                "minPeriod": "mm",
                "parseDates": true
            },
            "export": {
                "enabled": true,
                "dateFormat": "YYYY-MM-DD HH:NN:SS"
            }
        });

        chart.addListener("dataUpdated", zoomChart);
        // when we apply theme, the dataUpdated event is fired even before we add listener, so
        // we need to call zoomChart here
        zoomChart();
        // this method is called when chart is first inited as we listen for "dataUpdated" event
        function zoomChart() {
            // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
            chart.zoomToIndexes(chartData.length - 250, chartData.length - 100);
        }

        // generate some random data, quite different range
        function generateChartData() {
            var chartData = [];
            // current date
            var firstDate = new Date();
            // now set 500 minutes back
            firstDate.setMinutes(firstDate.getDate() - 1000);

            // and generate 500 data items
            for (var i = 0; i < 500; i++) {
                var newDate = new Date(firstDate);
                // each time we add one minute
                newDate.setMinutes(newDate.getMinutes() + i);
                // some random number
                var visits = Math.round(Math.random() * 40 + 10 + i + Math.random() * i / 5);
                // add data item to the array
                chartData.push({
                    date: newDate,
                    visits: visits
                });
            }
            return chartData;
        }
    </script>
    <script>
        var gaugeChart = AmCharts.makeChart("chartdiv1", {
            "type": "gauge",
            "theme": "light",
            "axes": [{
                "axisThickness": 1,
                "axisAlpha": 0.2,
                "tickAlpha": 0.2,
                "valueInterval": 20,
                "bands": [{
                    "color": "#84b761",
                    "endValue": 90,
                    "startValue": 0
                }, {
                    "color": "#fdd400",
                    "endValue": 130,
                    "startValue": 90
                }, {
                    "color": "#cc4748",
                    "endValue": 220,
                    "innerRadius": "95%",
                    "startValue": 130
                }],
                "bottomText": "0 km/h",
                "bottomTextYOffset": -20,
                "endValue": 220
            }],
            "arrows": [{}],
            "export": {
                "enabled": true
            }
        });

        setInterval(randomValue, 2000);

        // set random value
        function randomValue() {
            var value = Math.round(Math.random() * 200);
            if (gaugeChart) {
                if (gaugeChart.arrows) {
                    if (gaugeChart.arrows[0]) {
                        if (gaugeChart.arrows[0].setValue) {
                            gaugeChart.arrows[0].setValue(value);
                            gaugeChart.axes[0].setBottomText(value + " km/h");
                        }
                    }
                }
            }
        }
    </script>
    <script type="text/javascript">
        var sourceData = [];
        var firstDate = new Date();
        firstDate.setDate(firstDate.getDate() - 28);
        firstDate.setHours(0, 0, 0, 0);

        for (var i = 0; i < 28; i++) {
            var newDate = new Date(firstDate);
            newDate.setDate(newDate.getDate() + i);
            var dataPoint = {
                date: newDate
            }

            // generate value for each hour
            for (var h = 0; h <= 23; h++) {
                dataPoint['value' + h] = Math.round(Math.random() * 4);
            }

            sourceData.push(dataPoint);
        }

        // now let's populate the source data with the colors based on the value
        // as well as replace the original value with 1
        var colors = ['#FF0000', '#ff6666', '#FCD1D1', '#800000', '#A52A2A'];
        for (i in sourceData) {
            for (var h = 0; h <= 23; h++) {
                sourceData[i]['color' + h] = colors[sourceData[i]['value' + h]];
                sourceData[i]['hour' + h] = 1;
            }
        }

        // define graph objects for each hour
        var graphs = [];
        for (var h = 0; h <= 23; h++) {
            graphs.push({
                "balloonText": "Original value: [[value" + h + "]]",
                "fillAlphas": 1,
                "lineAlpha": 0,
                "type": "column",
                "colorField": "color" + h,
                "valueField": "hour" + h
            });
        }

        var chart = AmCharts.makeChart("chartdiv", {
            "type": "serial",
            "dataProvider": sourceData,
            "valueAxes": [{
                "stackType": "regular",
                "axisAlpha": 0.3,
                "gridAlpha": 0,
                "maximum": 24,
                "duration": "mm",
                "unit": ":00"
            }],
            "graphs": graphs,
            "columnWidth": 1,
            "categoryField": "date",
            "categoryAxis": {
                "parseDates": true,
                "gridPosition": "start",
                "axisAlpha": 0,
                "gridAlpha": 0,
                "position": "left"
            }
        });
    </script>

</body>
</html>
