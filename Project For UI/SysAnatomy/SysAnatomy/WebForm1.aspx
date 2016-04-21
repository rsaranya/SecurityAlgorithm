<!DOCTYPE HTML>
<!--
	Strongly Typed by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
    <title>SysAnatomy Home</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />


    <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="assets/css/main.css" />
    <%--<link rel="stylesheet" href="assets/css/BUTTONstyleSheet.css" />--%>
    <!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->

    <!--  <script src="http://code.highcharts.com/highcharts.js"></script> -->
    <!-- Scripts -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/jquery.dropotron.min.js"></script>
    <script src="assets/js/skel.min.js"></script>
    <script src="assets/js/skel-viewport.min.js"></script>
    <script src="assets/js/util.js"></script>
    <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
    <script src="assets/js/main.js"></script>
    <script type="text/javascript" src="http://www.amcharts.com/lib/3/amcharts.js"></script>
    <script type="text/javascript" src="http://www.amcharts.com/lib/3/serial.js"></script>
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

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <div class="row">
                    <div class="4u 12u(mobile)">
                        <!-- Feature -->
                        <section>
                            <a href="#" class="image featured">
                                <img src="images/pic01.jpg" alt="" /></a>
                            <header>
                                <h3>This is the Memory graph</h3>
                            </header>
                            
                        </section>
                    </div>
                    <div class="4u 12u(mobile)">

                        <!-- Feature -->
                        <section>
                            <a href="#" class="image featured">
                                <img src="images/pic02.jpg" alt="" /></a>
                            <header>
                                <h3>this is graph for no. of active processes</h3>
                            </header>
                            
                        </section>

                    </div>
                    <div class="4u 12u(mobile)">

                        <!-- Feature -->
                        <section>
                            <a href="#" class="image featured">
                                <img src="images/pic03.jpg" alt="" /></a>
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

    </div>
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
        var colors = ['#FF0000', '#ff6666', '#ffffff', '#800000', '#A52A2A'];
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
