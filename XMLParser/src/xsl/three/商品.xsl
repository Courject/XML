<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:apply-templates select="��Ʒ�б�"/>
	</xsl:template>
	<xsl:template match="��Ʒ�б�">
		<xsl:apply-templates select="��Ʒ">
			<xsl:sort select="�۸�" order="descending"/>
		</xsl:apply-templates>
	</xsl:template>
	<xsl:template match="��Ʒ">
	Ʒ��:[<xsl:value-of select="Ʒ��"/>]�۸�: <xsl:value-of select="�۸�"/><br/>
	</xsl:template>
</xsl:stylesheet>