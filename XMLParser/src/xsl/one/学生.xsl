<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0"  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<h2>����С��24���ѧ���У�</h2>
		<xsl:for-each select="ѧ���б�/ѧ��[����&lt;24and �ʼ�!=''] ">
			<xsl:value-of select="./@����"/><br/>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>