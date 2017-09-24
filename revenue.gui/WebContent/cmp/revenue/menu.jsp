<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%-- import menu xml --%>
<c:import var="xml_menu" url="cmp/revenue/cfg/menu.xml" />

<%-- import menu xslt --%>
<c:import var="xslt_menu" url="cmp/revenue/xslt/menu.xsl" />

<%--  build menu via xslt --%>
<x:transform xml="${xml_menu}" xslt="${xslt_menu}" />