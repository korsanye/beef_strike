package com.eyesopencrew;

/**
 * @author Beny Green - gacksecurity.blogspot.com
 *
 * EyesOpenCrew
 */

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class BeefReport {

    /**
     *
     */
    public static String repportf ;
    /**
     *
     */
    public static String title;
  /**
   *
   */
  public static String author ;
  /**
   *
   */
  public static String url ;
  /**
   *
   */
  public static String key ;
  /**
   *
   */
  public static String logo ;
  //private static Image Img ;
  private static Paragraph preface = new Paragraph();
  //
     private static String osname ;
            private static String datestamp ;
            private static String Hardware ;
            private static String CPU ;
            private static String screensize ;
            private static String touchenabled ;
            // Hooked page
            private static String pagetitle ;
            private static String pageuri ;
            private static String pagereferrer ;
            private static String hostname;
            // browser
            private static String browserplatform ;
            private static String browserplugins ;
            private static String browserreportedname ;
            private static String browserversion ;
            // components
            private static String HasActiveX ;
            private static String HasFlash ;
            private static String HasFoxit;
            private static String HasGoogleGears ;
            private static String HasPhonegap ;
            private static String HasQuickTime ;
            private static String HasRealPlayer;
            private static String HasSilverlight ;
            private static String HasVLC ;
            private static String HasWMP ;
            private static String HasWebRTC;
            private static String HasWebSocket;
            private static String JavaEnabled;
            private static String VBScriptEnabled;
          private static String hasPersistentCookies;
          private static String hasSessionCookies ;
  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
      Font.BOLD, BaseColor.BLUE);
   private static Font grayboldFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
      Font.BOLD, BaseColor.DARK_GRAY);
  private static Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
      Font.NORMAL);
  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
      Font.BOLD);
  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 14,
      Font.BOLD );

  /**
   *
   * @param document
   */
  public static void addMetaData(Document document) {
    document.addTitle("Penetration Test with Beef_Strike");
    document.addSubject("BeEF Report");
    document.addCreationDate();
    document.addKeywords("Armitage, Cobalt Strike, BeEF, XSS, EyesOpencrew, gacksecurity, beef_strike.cna, PDF");
    document.addCreator("gacksecurity.blogspot.com");
  }
  /**
   *
   * @param document
   * @throws DocumentException
   */
  public static void addTitlePage(Document document)
      throws DocumentException {
    
    // We add one empty line
    addEmptyLine(preface, 1);
    // Lets write a big header
    // your logo.
   
    Image img = null;
        try {
            img = Image.getInstance(logo);
        } catch (BadElementException ex) {
            Logger.getLogger(BeefReport.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("Image for logo not found !");
        } catch (MalformedURLException ex) {
            Logger.getLogger(BeefReport.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Image for logo not found !");
        } catch (IOException ex) {
            Logger.getLogger(BeefReport.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Image for logo not found !");
        }
 if (img != null) {
     img.scaleAbsolute(100f,90f );
     preface.add(img);
      }
     //title
    preface.add(new Paragraph(title, catFont));

    addEmptyLine(preface, 1);
    // Will create: Report generated by: _name, _date
    preface.add(new Paragraph("Report generated by: " + author + "" , //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        smallBold));
     preface.add(new Paragraph("Date : " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        smallBold));
    addEmptyLine(preface, 1);
    preface.add(new Paragraph("This document report the fingerprint of all the browsers hooked with BeEF during the penetration test. It demonstrate which kind of informations an attacker can obtain about the hosts inside the network. A hooked browser can represent a serious threat for the informations security. It can be used as a bridge for a silent the deployment of malicious artifacts inside the network." ,
        normalFont));

    addEmptyLine(preface, 2);

 }

  /**
   *
   * @param document
   * @throws DocumentException
   */
  public static void addContent(Document document) throws DocumentException {

   preface.add(new Paragraph("List of the browsers hooked",grayboldFont));

    // Beef parse
// REPORT: PROCESSING OFFLINE ZOMBIS
       String j = BeefRequester.BeefGetRequest("" + BeefReport.url + "/api/hooks?token=" + BeefReport.key + "");
   //    System.out.println(j);
       JSONObject js = new JSONObject();
try   {      js = (JSONObject) JSONSerializer.toJSON(j);
} catch (java.lang.ClassCastException e) {
    System.out.println(" ClassCastException : JSON object is null, Verify the URL of the BeEF server")     ;
    }
        Zombies[] on = OnlineClass.extractOnline(j);
        for (int i = 0; i < on.length; i++) {
            String sid = on[i].getSession();
            String url_k = "" + BeefReport.url + "/api/hooks/" + sid + "?token=" + BeefReport.key +  "";
     //       System.out.println(url_k);
            String k = BeefRequester.BeefGetRequest(url_k);
            JSONObject jk = new JSONObject();
            jk = (JSONObject) JSONSerializer.toJSON(k);
            String hookedbrowser = on[i].getIp();
            // host informations
                    
         if (jk.containsKey("OsName")){osname = jk.getString("OsName"); System.out.println(osname); }
            if (jk.containsKey("DateStamp")){  datestamp = jk.getString("DateStamp");}
            if (jk.containsKey("Hardware")){  Hardware = jk.getString("Hardware");}
            if (jk.containsKey("CPU")){  CPU = jk.getString("CPU");}
            if (jk.containsKey("ScreenSize")){  screensize = jk.getString("ScreenSize");}
            if (jk.containsKey("TouchEnabled")){  touchenabled = jk.getString("TouchEnabled");}
            // Hooked page
           if (jk.containsKey("PageTitle")){   pagetitle = jk.getString("PageTitle");}
            if (jk.containsKey("PageURI")){  pageuri = jk.getString("PageURI");}
            if (jk.containsKey("PageReferrer")){  pagereferrer = jk.getString("PageReferrer");}
            if (jk.containsKey("HostName")){  hostname = jk.getString("HostName");}
            // browser
            if (jk.containsKey("BrowserPlatform")){  browserplatform = jk.getString("BrowserPlatform");}
            if (jk.containsKey("BrowserPlugins")){  browserplugins = jk.getString("BrowserPlugins");}
            if (jk.containsKey("BrowserReportedName")){  browserreportedname = jk.getString("BrowserReportedName");}
            if (jk.containsKey("BrowserVersion")){  browserversion = jk.getString("BrowserVersion");}
            // components
            if (jk.containsKey("HasActiveX")){  HasActiveX = jk.getString("HasActiveX");}
            if (jk.containsKey("HasFlash")){  HasFlash = jk.getString("HasFlash");}
            if (jk.containsKey("HasFoxit")){  HasFoxit = jk.getString("HasFoxit");}
            if (jk.containsKey("HasGoogleGears")){  HasGoogleGears = jk.getString("HasGoogleGears");}
            if (jk.containsKey("HasPhonegap")){  HasPhonegap = jk.getString("HasPhonegap");}
            if (jk.containsKey("HasQuickTime")){  HasQuickTime = jk.getString("HasQuickTime");}
            if (jk.containsKey("HasRealPlayer")){  HasRealPlayer = jk.getString("HasRealPlayer");}
           if (jk.containsKey("HasSilverlight")){ HasSilverlight = jk.getString("HasSilverlight");}
           if (jk.containsKey("HasVLC")){   HasVLC = jk.getString("HasVLC");}
           if (jk.containsKey("HasWMP")){   HasWMP = jk.getString("HasWMP");}
           if (jk.containsKey("HasWebRTC")){   HasWebRTC = jk.getString("HasWebRTC");}
           if (jk.containsKey("HasWebSocket")){   HasWebSocket = jk.getString("HasWebSocket");}
           if (jk.containsKey("JavaEnabled")){   JavaEnabled = jk.getString("JavaEnabled");}
           if (jk.containsKey("VBScriptEnabled")){ VBScriptEnabled = jk.getString("VBScriptEnabled");}
          if (jk.containsKey("hasPersistentCookies")){   hasPersistentCookies = jk.getString("hasPersistentCookies");}
           if (jk.containsKey("hasSessionCookies")){ hasSessionCookies = jk.getString("hasSessionCookies");}
    //        System.out.println(JavaEnabled);
    preface.add(new Paragraph("Host: " + hookedbrowser + "", smallBold));

    preface.add(new Paragraph("    Informations", normalFont));

    PdfPTable table = new PdfPTable(2);
    PdfPCell c1 = new PdfPCell(new Phrase("Property"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(c1);
    PdfPCell c2 = new PdfPCell(new Phrase("Value"));
    c2.setHorizontalAlignment(Element.ALIGN_CENTER);
    c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(c2);
    table.setHeaderRows(2);
    table.addCell("Operating System");
    table.addCell(osname);
    table.addCell("Date");
    table.addCell(datestamp);
    table.addCell("Hardware");
    table.addCell(Hardware);
    table.addCell("CPU");
    table.addCell(CPU);
    table.addCell("Screen Size");
    table.addCell(screensize);
    table.addCell("Touch Enabled");
    table.addCell(touchenabled);
   preface.add(table);

 preface.add(new Paragraph("    Browser", normalFont));

    PdfPTable table3 = new PdfPTable(2);
    PdfPCell c13 = new PdfPCell(new Phrase("Property"));
    c13.setHorizontalAlignment(Element.ALIGN_CENTER);
    c13.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table3.addCell(c13);
    PdfPCell c23 = new PdfPCell(new Phrase("Value"));
    c23.setHorizontalAlignment(Element.ALIGN_CENTER);
    c23.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table3.addCell(c23);
    table3.setHeaderRows(2);
    table3.addCell("Version");
    table3.addCell(browserversion);
    table3.addCell("User Agent");
    table3.addCell(browserreportedname);
    table3.addCell("Platform");
    table3.addCell(browserplatform);
    table3.addCell("Plugins");
    table3.addCell(browserplugins);
   preface.add(table3);

 preface.add(new Paragraph("    Components", normalFont));

    PdfPTable table2 = new PdfPTable(2);
    PdfPCell c12 = new PdfPCell(new Phrase("Property"));
    c12.setHorizontalAlignment(Element.ALIGN_CENTER);
    c12.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table2.addCell(c12);
    PdfPCell c22 = new PdfPCell(new Phrase("Value"));
    c22.setHorizontalAlignment(Element.ALIGN_CENTER);
    c22.setBackgroundColor(BaseColor.LIGHT_GRAY);

    table2.addCell(c22);
    table2.setHeaderRows(2);
    if (HasActiveX.equalsIgnoreCase("yes")){
         table2.addCell("ActiveX ");
        table2.addCell(HasActiveX);
    }
         table2.addCell("Java Activated");
         table2.addCell(JavaEnabled);
         table2.addCell("Flash");
         table2.addCell(HasFlash);
    if (VBScriptEnabled.equalsIgnoreCase("yes")){
         table2.addCell("VBS Activated");
         table2.addCell(VBScriptEnabled);}
    if (HasFoxit.equalsIgnoreCase("yes")){
         table2.addCell("Foxit Reader");
         table2.addCell(HasFoxit);}
    if (HasGoogleGears.equalsIgnoreCase("yes")){
         table2.addCell("Google Gears");
         table2.addCell(HasGoogleGears);}
    if (HasPhonegap.equalsIgnoreCase("yes")){
         table2.addCell("Phonegap ");
         table2.addCell(HasPhonegap);}
    if (HasQuickTime.equalsIgnoreCase("yes")){
          table2.addCell("QuickTime ");
         table2.addCell(HasQuickTime);}
    if (HasSilverlight.equalsIgnoreCase("yes")){
         table2.addCell("SilverLight ");
        table2.addCell(HasSilverlight);}
    if (HasRealPlayer.equalsIgnoreCase("yes")){
         table2.addCell("Real Player ");
        table2.addCell(HasRealPlayer);}
    if (HasVLC.equalsIgnoreCase("yes")){
        table2.addCell("VLC ");
        table2.addCell(HasVLC);}
    if (HasWMP.equalsIgnoreCase("yes")){
         table2.addCell("Windows Media Player ");
        table2.addCell(HasWMP);}
    if (HasWebRTC.equalsIgnoreCase("yes")){
        table2.addCell("Web RTC ");
         table2.addCell(HasWebRTC);}
    if (HasWebSocket.equalsIgnoreCase("yes")){
          table2.addCell("Web Sockets ");
         table2.addCell(HasWebSocket);}
    if (hasSessionCookies.equalsIgnoreCase("yes")){
       table2.addCell("Session Cookies");
        table2.addCell(hasSessionCookies);}
    if (hasPersistentCookies.equalsIgnoreCase("yes")){
         table2.addCell("Persistent Cookies");
    table2.addCell(hasPersistentCookies);}
       preface.add(table2);

   preface.add(new Paragraph("Hooked page", normalFont));

    PdfPTable table4 = new PdfPTable(2);
    PdfPCell c14 = new PdfPCell(new Phrase("Property"));
    c14.setHorizontalAlignment(Element.ALIGN_CENTER);
    c14.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table4.addCell(c14);
    PdfPCell c24 = new PdfPCell(new Phrase("Value"));
    c24.setHorizontalAlignment(Element.ALIGN_CENTER);
    c24.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table4.addCell(c24);
    table4.setHeaderRows(2);
    table4.addCell("Page Title");
    table4.addCell(pagetitle);
    table4.addCell("page URI");
    table4.addCell(pageuri);
    table4.addCell("Page referrer");
    table4.addCell(pagereferrer);
    table4.addCell("Hostname");
    table4.addCell(hostname);
   preface.add(table4);
        }

// REPORT: PROCESSING OFFLINE ZOMBIS
        Zombies[] off = OfflineClass.extractOffline(j);
        for (int i = 0; i < off.length; i++) {
            String sid = off[i].getSession();
            String url_ko = "" + BeefReport.url + "/api/hooks/" + sid + "?token=" + BeefReport.key +  "";
     //       System.out.println(url_k);
            String ko = BeefRequester.BeefGetRequest(url_ko);
            JSONObject jko = new JSONObject();
            jko = (JSONObject) JSONSerializer.toJSON(ko);
            String hookedbrowser = off[i].getIp();
            // host informations
             if (jko.containsKey("OsName")){osname = jko.getString("OsName");}
            if (jko.containsKey("DateStamp")){  datestamp = jko.getString("DateStamp");}
            if (jko.containsKey("Hardware")){  Hardware = jko.getString("Hardware");}
            if (jko.containsKey("CPU")){  CPU = jko.getString("CPU");}
            if (jko.containsKey("ScreenSize")){  screensize = jko.getString("ScreenSize");}
            if (jko.containsKey("TouchEnabled")){  touchenabled = jko.getString("TouchEnabled");}
            // Hooked page
           if (jko.containsKey("PageTitle")){   pagetitle = jko.getString("PageTitle");}
            if (jko.containsKey("PageURI")){  pageuri = jko.getString("PageURI");}
            if (jko.containsKey("PageReferrer")){  pagereferrer = jko.getString("PageReferrer");}
            if (jko.containsKey("HostName")){  hostname = jko.getString("HostName");}
            // browser
            if (jko.containsKey("BrowserPlatform")){  browserplatform = jko.getString("BrowserPlatform");}
            if (jko.containsKey("BrowserPlugins")){  browserplugins = jko.getString("BrowserPlugins");}
            if (jko.containsKey("BrowserReportedName")){  browserreportedname = jko.getString("BrowserReportedName");}
            if (jko.containsKey("BrowserVersion")){  browserversion = jko.getString("BrowserVersion");}
            // components
            if (jko.containsKey("HasActiveX")){  HasActiveX = jko.getString("HasActiveX");}
            if (jko.containsKey("HasFlash")){  HasFlash = jko.getString("HasFlash");}
            if (jko.containsKey("HasFoxit")){  HasFoxit = jko.getString("HasFoxit");}
            if (jko.containsKey("HasGoogleGears")){  HasGoogleGears = jko.getString("HasGoogleGears");}
            if (jko.containsKey("HasPhonegap")){  HasPhonegap = jko.getString("HasPhonegap");}
            if (jko.containsKey("HasQuickTime")){  HasQuickTime = jko.getString("HasQuickTime");}
            if (jko.containsKey("HasRealPlayer")){  HasRealPlayer = jko.getString("HasRealPlayer");}
           if (jko.containsKey("HasSilverlight")){ HasSilverlight = jko.getString("HasSilverlight");}
           if (jko.containsKey("HasVLC")){   HasVLC = jko.getString("HasVLC");}
           if (jko.containsKey("HasWMP")){   HasWMP = jko.getString("HasWMP");}
           if (jko.containsKey("HasWebRTC")){   HasWebRTC = jko.getString("HasWebRTC");}
           if (jko.containsKey("HasWebSocket")){   HasWebSocket = jko.getString("HasWebSocket");}
           if (jko.containsKey("JavaEnabled")){   JavaEnabled = jko.getString("JavaEnabled");}
           if (jko.containsKey("VBScriptEnabled")){ VBScriptEnabled = jko.getString("VBScriptEnabled");}
          if (jko.containsKey("hasPersistentCookies")){   hasPersistentCookies = jko.getString("hasPersistentCookies");}
           if (jko.containsKey("hasSessionCookies")){ hasSessionCookies = jko.getString("hasSessionCookies");}
    //        System.out.println(JavaEnabled);
    preface.add(new Paragraph("Host: " + hookedbrowser + "", smallBold));

    preface.add(new Paragraph("    Informations", normalFont));

    PdfPTable table = new PdfPTable(2);
    PdfPCell c1 = new PdfPCell(new Phrase("Property"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(c1);
    PdfPCell c2 = new PdfPCell(new Phrase("Value"));
    c2.setHorizontalAlignment(Element.ALIGN_CENTER);
    c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table.addCell(c2);
    table.setHeaderRows(2);
    table.addCell("Operating System");
    table.addCell(osname);
    table.addCell("Date");
    table.addCell(datestamp);
    table.addCell("Hardware");
    table.addCell(Hardware);
    table.addCell("CPU");
    table.addCell(CPU);
    table.addCell("Screen Size");
    table.addCell(screensize);
    table.addCell("Touch Enabled");
    table.addCell(touchenabled);
   preface.add(table);

 preface.add(new Paragraph("    Browser", normalFont));

    PdfPTable table3 = new PdfPTable(2);
    PdfPCell c13 = new PdfPCell(new Phrase("Property"));
    c13.setHorizontalAlignment(Element.ALIGN_CENTER);
    c13.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table3.addCell(c13);
    PdfPCell c23 = new PdfPCell(new Phrase("Value"));
    c23.setHorizontalAlignment(Element.ALIGN_CENTER);
    c23.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table3.addCell(c23);
    table3.setHeaderRows(2);
    table3.addCell("Version");
    table3.addCell(browserversion);
    table3.addCell("User Agent");
    table3.addCell(browserreportedname);
    table3.addCell("Platform");
    table3.addCell(browserplatform);
    table3.addCell("Plugins");
    table3.addCell(browserplugins);
   preface.add(table3);

 preface.add(new Paragraph("    Components", normalFont));
    PdfPTable table2 = new PdfPTable(2);
    PdfPCell c12 = new PdfPCell(new Phrase("Property"));
    c12.setHorizontalAlignment(Element.ALIGN_CENTER);
    c12.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table2.addCell(c12);
    PdfPCell c22 = new PdfPCell(new Phrase("Value"));
    c22.setHorizontalAlignment(Element.ALIGN_CENTER);
    c22.setBackgroundColor(BaseColor.LIGHT_GRAY);

       table2.addCell(c22);
    table2.setHeaderRows(2);
    if (HasActiveX.equalsIgnoreCase("yes")){
         table2.addCell("ActiveX ");
        table2.addCell(HasActiveX);
    }
         table2.addCell("Java Activated");
         table2.addCell(JavaEnabled);
         table2.addCell("Flash");
         table2.addCell(HasFlash);
    if (VBScriptEnabled.equalsIgnoreCase("yes")){
         table2.addCell("VBS Activated");
         table2.addCell(VBScriptEnabled);}
    if (HasFoxit.equalsIgnoreCase("yes")){
         table2.addCell("Foxit Reader");
         table2.addCell(HasFoxit);}
    if (HasGoogleGears.equalsIgnoreCase("yes")){
         table2.addCell("Google Gears");
         table2.addCell(HasGoogleGears);}
    if (HasPhonegap.equalsIgnoreCase("yes")){
         table2.addCell("Phonegap ");
         table2.addCell(HasPhonegap);}
    if (HasQuickTime.equalsIgnoreCase("yes")){
          table2.addCell("QuickTime ");
         table2.addCell(HasQuickTime);}
    if (HasSilverlight.equalsIgnoreCase("yes")){
         table2.addCell("SilverLight ");
        table2.addCell(HasSilverlight);}
    if (HasRealPlayer.equalsIgnoreCase("yes")){
         table2.addCell("Real Player ");
        table2.addCell(HasRealPlayer);}
    if (HasVLC.equalsIgnoreCase("yes")){
        table2.addCell("VLC ");
        table2.addCell(HasVLC);}
    if (HasWMP.equalsIgnoreCase("yes")){
         table2.addCell("Windows Media Player ");
        table2.addCell(HasWMP);}
    if (HasWebRTC.equalsIgnoreCase("yes")){
        table2.addCell("Web RTC ");
         table2.addCell(HasWebRTC);}
    if (HasWebSocket.equalsIgnoreCase("yes")){
          table2.addCell("Web Sockets ");
         table2.addCell(HasWebSocket);}
    if (hasSessionCookies.equalsIgnoreCase("yes")){
       table2.addCell("Session Cookies");
        table2.addCell(hasSessionCookies);}
    if (hasPersistentCookies.equalsIgnoreCase("yes")){
         table2.addCell("Persistent Cookies");
    table2.addCell(hasPersistentCookies);}
       preface.add(table2);
//addEmptyLine(subPara, 1);
   preface.add(new Paragraph("Hooked page", normalFont));
   // addEmptyLine(subPara, 2);
    PdfPTable table4 = new PdfPTable(2);
    PdfPCell c14 = new PdfPCell(new Phrase("Property"));
    c14.setHorizontalAlignment(Element.ALIGN_CENTER);
    c14.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table4.addCell(c14);
    PdfPCell c24 = new PdfPCell(new Phrase("Value"));
    c24.setHorizontalAlignment(Element.ALIGN_CENTER);
    c24.setBackgroundColor(BaseColor.LIGHT_GRAY);
    table4.addCell(c24);
    table4.setHeaderRows(2);
    table4.addCell("Page Title");
    table4.addCell(pagetitle);
    table4.addCell("page URI");
    table4.addCell(pageuri);
    table4.addCell("Page referrer");
    table4.addCell(pagereferrer);
    table4.addCell("Hostname");
    table4.addCell(hostname);
   preface.add(table4);
     }

    Paragraph paragraph = new Paragraph();
   addEmptyLine(paragraph, 5);

    document.add(preface);
  }

   
 public static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }

 /**
  *
  * @param repportfile
  * @param title
  * @param author
  * @param url
  * @param key
  * @param logo
  */
 public static void generateReport(String repportfile, String title, String author, String url, String key, String logo ) {
        BeefReport.title = title ;
        BeefReport.author = author ;
        BeefReport.repportf = repportfile ;
        BeefReport.url = url ;
        BeefReport.key = key ;
        BeefReport.logo = logo ;
        String FILE = BeefReport.repportf  ;
      Document document = new Document();
      try {
      PdfWriter.getInstance(document, new FileOutputStream(FILE));
      document.open();
      BeefReport.addMetaData(document);
      BeefReport.addTitlePage(document);
      BeefReport.addContent(document);
      document.close();
    } catch (Exception e) {
    }
    }
}