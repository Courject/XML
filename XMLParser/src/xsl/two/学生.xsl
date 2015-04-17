<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<h2>年龄大于24岁的学生有：</h2>
		<xsl:apply-templates select="学生列表"/>
	</xsl:template>
	<xsl:template match="学生列表">
	<xsl:apply-templates select="学生"/>
	</xsl:template>
	<xsl:template match="学生">
	<xsl:if test="年龄[.&gt;24]">
	<font color="red">
	<xsl:value-of select="./@姓名"/>
	</font>
	</xsl:if>
	</xsl:template>
</xsl:stylesheet>