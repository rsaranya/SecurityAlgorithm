<!DOCTYPE HTML>
<!--
	This page displays information about the CPU
-->
<html>
	<head>
		<title>CPU Info</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
        <script src="amcharts/amcharts.js" type="text/javascript"></script>
        <script src="amcharts/serial.js" type="text/javascript"></script>
	</head>
	<body class="left-sidebar">
		<div id="page-wrapper">

			<!-- Header -->
				<div id="header-wrapper">
            <div id="header" class="container">

                <!-- Logo -->
                <h1 id="logo"><a href="Home.aspx">Sys-Anatomy</a></h1>
                <p>A reporting tool that helps you know your System better.</p>
                    
                <!-- Nav -->
                <nav id="nav">
                    <ul>
                        <li><a class="icon fa-home" href="Home.aspx"><span>Go Home</span></a></li>
                       <li><a class="icon fa-cog" href="AboutYourPC.html"><span>About your PC</span></a></li>

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
                        <li><a class="icon fa-retweet" href="CPUStats.aspx"><span>CPU Stats</span></a></li>
                        <li><a class="icon fa-sitemap" href="#"><span>File System</span></a></li>
                        <li><a class="icon fa-bar-chart-o" href="SummarisedReport.html"><span>Summarized Report</span></a></li>
                    </ul>
                </nav>

            </div>
        </div>

			<!-- Main -->
				<div id="main-wrapper">
					<div id="main" class="container">
						<div class="row">
                            <!-- Graph-->
                             <div id="chartdiv2" style="width: 1240px; height: 400px;"></div>
							<!-- Sidebar -->
								<div id="sidebar" class="4u 12u(mobile)">

									<!-- Excerpts -->
										<section>
											<ul class="divided">
												<li>

													<!-- Excerpt -->
														<article class="box excerpt">
															<header>
																<span class="date"></span>
																<h3><a href="#">CPU information</a></h3>
															</header>
															<p> Date, time, ip adress, no. of cores, etc</p>
														</article>

												</li>
												<li>

													<!-- Excerpt -->
														<article class="box excerpt">
															<header>
																<span class="date"></span>
																<h3><a href="#">Ram/Memory</a></h3>
															</header>
															
														</article>

												</li>
												<li>

													<!-- Excerpt -->
														<article class="box excerpt">
															<header>
																<span class="date"></span>
																<h3><a href="#">Clock Speed</a></h3>
															</header>
															
														</article>

												</li>
											</ul>
										</section>

									<!-- Highlights -->
										<section>
											<ul class="divided">
												<li>

													<!-- Highlight -->
														<article class="box highlight">
															<header>
																<h3><a href="#">Something of note</a></h3>
															</header>
															<a href="#" class="image left"><img src="images/pic06.jpg" alt="" /></a>
															
															<ul class="actions">
																<li><a href="#" class="button icon fa-file">Learn More</a></li>
															</ul>
														</article>

												</li>
												<li>

													<!-- Highlight -->
														<article class="box highlight">
															<header>
																<h3><a href="#">Something of less note</a></h3>
															</header>
															<a href="#" class="image left"><img src="images/pic07.jpg" alt="" /></a>
															<ul class="actions">
																<li><a href="#" class="button icon fa-file">Learn More</a></li>
															</ul>
														</article>

												</li>
											</ul>
										</section>

								</div>

                            [0
							<!-- Content -->
								

						</div>
					</div>
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
                                        <li class="icon fa-home">Marist College, North Road<br />
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

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.dropotron.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/skel-viewport.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

        
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
	</body>
</html>