<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="html" encoding="UTF-8" indent="yes" />

	<!-- template revenue -->
	<xsl:template match="/revenue">

		<nav>
			<xsl:attribute name="class">navbar navbar-default navbar-fixed-bottom</xsl:attribute>

			<div>
				<xsl:attribute name="class">container-fluit</xsl:attribute>

				<div>
					<xsl:attribute name="class">collapse navbar-collapse</xsl:attribute>

					<xsl:apply-templates select="menuleft" />

					<xsl:apply-templates select="menuright" />

				</div>
			</div>
		</nav>

	</xsl:template>

	<!-- template menu left -->
	<xsl:template match="menuleft">

		<ul>
			<xsl:attribute name="class">nav navbar-nav navbar-left</xsl:attribute>

			<!-- menu -->
			<xsl:apply-templates select="menu" />
		</ul>

	</xsl:template>

	<!-- template menu right -->
	<xsl:template match="menuright">

		<ul>
			<xsl:attribute name="class">nav navbar-nav navbar-right</xsl:attribute>

			<!-- menu -->
			<xsl:apply-templates select="menu" />
		</ul>

	</xsl:template>

	<!-- template menu -->
	<xsl:template match="menu">

		<li>
			<xsl:attribute name="class">dropup</xsl:attribute>

			<a>
				<xsl:attribute name="href">#</xsl:attribute>
				<xsl:attribute name="class">dropdown-toggle</xsl:attribute>
				<xsl:attribute name="data-toggle">dropdown</xsl:attribute>
				<xsl:attribute name="role">button</xsl:attribute>
				<xsl:attribute name="aria-haspopup">true</xsl:attribute>
				<xsl:attribute name="aria-expanded">false</xsl:attribute>
				<span>
					<xsl:attribute name="id">
						<xsl:value-of select="id" />
					</xsl:attribute>
				</span>
				<span>
					<xsl:attribute name="class">caret</xsl:attribute>
				</span>
			</a>

			<!-- sub menu -->
			<xsl:apply-templates select="submenu" />
		</li>

	</xsl:template>

	<!-- template submenu -->
	<xsl:template match="submenu">

		<ul>
			<xsl:attribute name="class">dropdown-menu</xsl:attribute>

			<xsl:apply-templates select="subitem" />

			<xsl:apply-templates select="subitemlang" />
		</ul>

	</xsl:template>

	<!-- template subitem -->
	<xsl:template match="subitem">

		<xsl:choose>
			<xsl:when test="divider">
				<li>
					<xsl:attribute name="role">separator</xsl:attribute>
					<xsl:attribute name="class">divider</xsl:attribute>
				</li>
			</xsl:when>
			<xsl:when test="submenu">
				<li>
					<xsl:attribute name="class">dropdown-submenu</xsl:attribute>
					<a>
						<xsl:attribute name="href">#</xsl:attribute>
						<xsl:attribute name="id">
							<xsl:value-of select="id" />
						</xsl:attribute>
					</a>
					<xsl:apply-templates select="submenu" />
				</li>
			</xsl:when>
			<xsl:otherwise>
				<li>
					<a>
						<xsl:attribute name="href">#</xsl:attribute>
						<xsl:attribute name="onclick">load_cmp_view('<xsl:value-of select="path" />cmp.jsp');</xsl:attribute>
						<xsl:attribute name="id">
							<xsl:value-of select="id" />
						</xsl:attribute>
					</a>
				</li>
			</xsl:otherwise>
		</xsl:choose>

	</xsl:template>

	<!-- template subitem language -->
	<xsl:template match="subitemlang">
		<li>
			<a>
			    <xsl:attribute name="onclick">translate($('#<xsl:value-of select="id" />').text());</xsl:attribute>
				<xsl:attribute name="href">#</xsl:attribute>
				<img>
					<xsl:attribute name="src"><xsl:value-of select="icon" /></xsl:attribute>
				</img>
				<xsl:text> </xsl:text>
				<span>
					<xsl:attribute name="id">
						<xsl:value-of select="id" />
					</xsl:attribute>
				</span>
			</a>
		</li>
	</xsl:template>

</xsl:stylesheet>