<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<h2>年龄小于24岁的学生有：</h2>
		<xsl:for-each select="学生列表/学生[年龄&lt;24and 邮件!=''] ">
			<xsl:value-of select="./@姓名"/><br/>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>