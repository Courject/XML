<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<h2>�������24���ѧ���У�</h2>
		<xsl:apply-templates select="ѧ���б�"/>
	</xsl:template>
	<xsl:template match="ѧ���б�">
	<xsl:apply-templates select="ѧ��"/>
	</xsl:template>
	<xsl:template match="ѧ��">
	<xsl:if test="����[.&gt;24]">
	<font color="red">
	<xsl:value-of select="./@����"/>
	</font>
	</xsl:if>
	</xsl:template>
</xsl:stylesheet>