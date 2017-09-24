<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- template roots -->
	<xsl:template match="/roots">

		<nav class="navbar navbar-default navbar-fixed-bottom">
			<div class="container-fluit">
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-left">

						<!-- menu -->
						<xsl:apply-templates select="menu" />

					</ul>
				</div>
			</div>
		</nav>

	</xsl:template>

	<!-- template menu -->
	<xsl:template match="menu">

		<li class="dropup">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
				<xsl:value-of select="id" />
				<span class="caret"></span>
			</a>
		</li>

	</xsl:template>

</xsl:stylesheet>

<!-- <nav class="navbar navbar-default navbar-fixed-bottom"> -->

<!-- <div class="container-fluit"> -->

<!-- <div class="collapse navbar-collapse"> -->

<!-- <ul class="nav navbar-nav navbar-left"> -->

<!-- Menu -->
<!-- <x:forEach select="$xml_menu_out/roots/menu" var="menu"> -->

<!-- <li class="dropup"> -->
<!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> -->
<!-- <x:out select="$menu/id" /> -->
<!-- <span class="caret"></span> -->
<!-- </a> -->
<!-- Sub.Menu -->

<!-- <ul class="dropdown-menu"> -->

<!-- <li class="dropdown-submenu"><a href="#" id="roots.menu.editors">Editors</a> -->
<!-- <ul class="dropdown-menu"> -->
<!-- <li><a onclick="load_cmp_view('cmp/editors/curriculum_vitae/cmp.snip');">Curriculum Vitae</a></li> -->
<!-- <li><a href="#">Persona</a></li> -->
<!-- </ul></li> -->

<!-- <li><a href="#">Reports</a></li> -->
<!-- <li><a href="#">Statistics</a></li> -->
<!-- <li role="separator" class="divider"></li> -->
<!-- <li><a href="#">Import</a></li> -->
<!-- <li><a href="#">Export</a></li> -->

<!-- </ul> -->
<!-- </li> -->

<!-- </x:forEach> -->

<!-- Menu.Repository -->
<!-- <li class="dropup"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Repository <span class="caret"></span></a> -->

<!-- <ul class="dropdown-menu"> -->
<!-- <li><a href="#">Open Repository</a></li> -->
<!-- <li><a href="#">Create Repository</a></li> -->
<!-- </ul></li> -->
<!-- Menu.Info -->
<!-- <li class="dropup"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Info <span class="caret"></span></a> -->

<!-- <ul class="dropdown-menu"> -->
<!-- <li><a onclick="load_cmp_view('cmp/info/about/cmp.snip');">About</a></li> -->
<!-- </ul></li> -->

<!-- </ul> -->

<!-- <ul class="nav navbar-nav navbar-right"> -->

<!-- Menu-Language -->
<!-- <li class="dropup"> -->
<!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> -->
<!-- EN -->
<!-- <span class="caret"></span> -->
<!-- </a> -->
<!-- <ul class="dropdown-menu"> -->
<!-- <li> -->
<!-- <a href="#">DE</a> -->
<!-- </li> -->
<!-- </ul> -->
<!-- </li> -->

<!-- </ul> -->

<!-- </div> -->

<!-- </div> -->
<!-- </nav> -->

<!-- ################################################################ -->

<!-- <nav class="navbar navbar-default navbar-fixed-bottom"> -->

<!-- <div class="container-fluit"> -->

<!-- <div class="collapse navbar-collapse"> -->

<!-- <ul class="nav navbar-nav navbar-left"> -->

<!-- Menu.Roots -->


<!-- <li class="dropup"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Roots <span class="caret"></span></a> -->
<!-- <ul class="dropdown-menu"> -->

<!-- <li class="dropdown-submenu"><a href="#" id="roots.menu.editors">Editors</a> -->
<!-- <ul class="dropdown-menu"> -->
<!-- <li><a onclick="load_cmp_view('cmp/editors/curriculum_vitae/cmp.snip');">Curriculum Vitae</a></li> -->
<!-- <li><a href="#">Persona</a></li> -->
<!-- </ul></li> -->

<!-- <li><a href="#">Reports</a></li> -->
<!-- <li><a href="#">Statistics</a></li> -->
<!-- <li role="separator" class="divider"></li> -->
<!-- <li><a href="#">Import</a></li> -->
<!-- <li><a href="#">Export</a></li> -->

<!-- </ul> -->
<!-- </li> -->

<!-- Menu.Repository -->
<!-- <li class="dropup"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Repository <span class="caret"></span></a> -->

<!-- <ul class="dropdown-menu"> -->
<!-- <li><a href="#">Open Repository</a></li> -->
<!-- <li><a href="#">Create Repository</a></li> -->
<!-- </ul></li> -->
<!-- Menu.Info -->
<!-- <li class="dropup"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Info <span class="caret"></span></a> -->

<!-- <ul class="dropdown-menu"> -->
<!-- <li><a onclick="load_cmp_view('cmp/info/about/cmp.snip');">About</a></li> -->
<!-- </ul></li> -->

<!-- </ul> -->

<!-- <ul class="nav navbar-nav navbar-right"> -->

<!-- Menu-Language -->
<!-- <li class="dropup"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">EN <span class="caret"></span></a> -->
<!-- <ul class="dropdown-menu"> -->
<!-- <li><a href="#">DE</a></li> -->
<!-- </ul></li> -->

<!-- </ul> -->

<!-- </div> -->

<!-- </div> -->
<!-- </nav> -->