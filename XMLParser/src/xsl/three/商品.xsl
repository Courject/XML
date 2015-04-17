<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:apply-templates select="商品列表"/>
	</xsl:template>
	<xsl:template match="商品列表">
		<xsl:apply-templates select="商品">
			<xsl:sort select="价格" order="descending"/>
		</xsl:apply-templates>
	</xsl:template>
	<xsl:template match="商品">
	品名:[<xsl:value-of select="品名"/>]价格: <xsl:value-of select="价格"/><br/>
	</xsl:template>
</xsl:stylesheet>