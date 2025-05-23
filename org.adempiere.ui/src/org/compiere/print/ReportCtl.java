/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.print;

import static org.compiere.model.SystemIDs.PROCESS_RPT_C_DUNNING;
import static org.compiere.model.SystemIDs.PROCESS_RPT_C_INVOICE;
import static org.compiere.model.SystemIDs.PROCESS_RPT_C_ORDER;
import static org.compiere.model.SystemIDs.PROCESS_RPT_C_PAYMENT;
import static org.compiere.model.SystemIDs.PROCESS_RPT_C_PROJECT;
import static org.compiere.model.SystemIDs.PROCESS_RPT_C_RFQRESPONSE;
import static org.compiere.model.SystemIDs.PROCESS_RPT_FINREPORT;
import static org.compiere.model.SystemIDs.PROCESS_RPT_FINSTATEMENT;
import static org.compiere.model.SystemIDs.PROCESS_RPT_M_INOUT;
import static org.compiere.model.SystemIDs.PROCESS_RPT_M_INVENTORY;
import static org.compiere.model.SystemIDs.PROCESS_RPT_M_MOVEMENT;

import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.base.IServiceReferenceHolder;
import org.adempiere.base.Service;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.IProcessUI;
import org.compiere.model.MPInstance;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MProcess;
import org.compiere.model.MQuery;
import org.compiere.model.MTable;
import org.compiere.model.PrintInfo;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *	Report Controller.
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ReportCtl.java,v 1.3 2006/10/08 07:05:08 comdivision Exp $
 *
 *  @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>FR [ 1866739 ] ReportCtl: use printformat from the transient/serializable
 */
public class ReportCtl
{
	/**
	 * @deprecated Please use {@link ServerReportCtl#PARAM_PRINTER_NAME}
	 */
	@Deprecated	
	public static final String PARAM_PRINTER_NAME = ServerReportCtl.PARAM_PRINTER_NAME;
	/**
	 * @deprecated Please use {@link ServerReportCtl#PARAM_PRINT_FORMAT}
	 */
	@Deprecated
	public static final String PARAM_PRINT_FORMAT = ServerReportCtl.PARAM_PRINT_FORMAT;
	/**
	 * @deprecated Please use {@link ServerReportCtl#PARAM_PRINT_INFO}
	 */
	@Deprecated
	public static final String PARAM_PRINT_INFO = ServerReportCtl.PARAM_PRINT_INFO;

	/**
	 *	Constructor - prevent instance
	 */
	private ReportCtl()
	{
	}	//	ReportCtrl

	/**	Static Logger	*/
	private static final CLogger	s_log	= CLogger.getCLogger (ReportCtl.class);
	private volatile static ProcessInfo m_pi;

	/**
	 *	Create Report.<br/>
	 *	Called from ProcessCtl.<br/>
	 *	- Check special reports first, if not, create standard Report
	 *
	 *  @param pi process info
	 *  @param IsDirectPrint if true, prints directly - otherwise View
	 *  @return true if created
	 */
	static public boolean start (ProcessInfo pi, boolean IsDirectPrint)
	{
		return start(null, -1, pi, IsDirectPrint);
	}

	/**
	 *	Create Report.<br/>
	 *	Called from ProcessCtl.<br/>
	 *	- Check special reports first, if not, create standard Report
	 *
	 *  @param parent The window which invoked the printing
	 *  @param WindowNo The windows number which invoked the printing
	 *  @param pi process info
	 *  @param IsDirectPrint if true, prints directly - otherwise View
	 *  @return true if created
	 */
	static public boolean start (IProcessUI parent, int WindowNo, ProcessInfo pi, boolean IsDirectPrint)
	{
		pi.setPrintPreview(!IsDirectPrint);
		return start(parent, WindowNo, pi);
	}

	/**
	 *	Create Report.<br/>
	 *	Called from ProcessCtl.<br/>
	 *	- Check special reports first, if not, create standard Report
	 *
	 *  @param parent The window which invoked the printing
	 *  @param WindowNo The windows number which invoked the printing
	 *  @param pi process info
	 *  @return true if created
	 */
	static public boolean start (IProcessUI parent, int WindowNo, ProcessInfo pi)
	{
		if (s_log.isLoggable(Level.INFO)) s_log.info("start - " + pi);

		m_pi = pi;

		MPInstance instance = new MPInstance(Env.getCtx(), pi.getAD_PInstance_ID(), null);

		if (pi.getSerializableObject() != null)
			instance.setAD_PrintFormat_ID(((MPrintFormat)pi.getSerializableObject()).getAD_PrintFormat_ID());
		
		if (pi.getReportType() != null)
			instance.setReportType(pi.getReportType());
		else if(instance.getAD_PrintFormat_ID() > 0)
			ReportEngine.setDefaultReportTypeToPInstance(Env.getCtx(), instance, instance.getAD_PrintFormat_ID());
		
		instance.setIsSummary(pi.isSummary());
		instance.setAD_Language_ID(pi.getLanguageID());
		instance.setIsProcessing(true);
		instance.saveEx();

		try {
			/**
			 *	Order Print
			 */
			if (pi.getAD_Process_ID() == PROCESS_RPT_C_ORDER)			//	C_Order
				return startDocumentPrint(ReportEngine.ORDER, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview(), instance);
			if (pi.getAD_Process_ID() ==  MProcess.getProcess_ID("Rpt PP_Order", null))			//	C_Order
				return startDocumentPrint(ReportEngine.MANUFACTURING_ORDER, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview(), instance);
			if (pi.getAD_Process_ID() ==  MProcess.getProcess_ID("Rpt DD_Order", null))			//	C_Order
				return startDocumentPrint(ReportEngine.DISTRIBUTION_ORDER, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview(), instance);
			else if (pi.getAD_Process_ID() == PROCESS_RPT_C_INVOICE)		//	C_Invoice
				return startDocumentPrint(ReportEngine.INVOICE, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview(), instance);
			else if (pi.getAD_Process_ID() == PROCESS_RPT_M_INOUT)		//	M_InOut
				return startDocumentPrint(ReportEngine.SHIPMENT, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview(), instance);
			else if (pi.getAD_Process_ID() == PROCESS_RPT_C_PROJECT)		//	C_Project
				return startDocumentPrint(ReportEngine.PROJECT, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview(), instance);
			else if (pi.getAD_Process_ID() == PROCESS_RPT_C_RFQRESPONSE)		//	C_RfQResponse
				return startDocumentPrint(ReportEngine.RFQ, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview(), instance);
			else if (pi.getAD_Process_ID() == PROCESS_RPT_C_PAYMENT)		//	C_Payment
				return startCheckPrint(pi.getRecord_ID(), !pi.isPrintPreview(), instance);
			else if (pi.getAD_Process_ID() == PROCESS_RPT_M_INVENTORY)		//	Physical Inventory
				return startDocumentPrint(ReportEngine.INVENTORY, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview(), instance);
			else if (pi.getAD_Process_ID() == PROCESS_RPT_M_MOVEMENT)		//	Inventory Move
				return startDocumentPrint(ReportEngine.MOVEMENT, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview(), instance);
			else if (pi.getAD_Process_ID() == PROCESS_RPT_C_DUNNING)		//	Dunning
				return startDocumentPrint(ReportEngine.DUNNING, pi.getRecord_ID(), parent, WindowNo, !pi.isPrintPreview());
		    else if (pi.getAD_Process_ID() == PROCESS_RPT_FINREPORT			//	Financial Report
				|| pi.getAD_Process_ID() == PROCESS_RPT_FINSTATEMENT)			//	Financial Statement
			   return startFinReport (pi, WindowNo, instance);
			/********************
			 *	Standard Report
			 *******************/
			return startStandardReport (pi, WindowNo, instance);
		}
		finally {
			instance.setIsProcessing(false);
			instance.saveEx();
		}
	}	//	create

	/**
	 *	Start Standard Report.<br/>
	 *  - Get Table Info and submit
	 *  @param pi Process Info
	 *  @param IsDirectPrint if true, prints directly - otherwise View
	 *  @return true if OK
	 */
	static public boolean startStandardReport (ProcessInfo pi, boolean IsDirectPrint)
	{
		return startStandardReport(pi, -1, IsDirectPrint);
	}
	
	/**
	 *	Start Standard Report.<br/>
	 *  - Get Table Info and submit
	 *  @param pi Process Info
	 *  @param WindowNo The windows number which invoked the printing
	 *  @param IsDirectPrint if true, prints directly - otherwise View
	 *  @return true if OK
	 */
	static public boolean startStandardReport (ProcessInfo pi, int WindowNo, boolean IsDirectPrint)
	{
		return startStandardReport(pi, WindowNo, IsDirectPrint, null);
	}
	
	/**
	 *	Start Standard Report.<br/>
	 *  - Get Table Info and submit
	 *  @param pi Process Info
	 *  @param WindowNo The windows number which invoked the printing
	 *  @param IsDirectPrint if true, prints directly - otherwise View
	 * 	@param instance - AD_PInstance
	 *  @return true if OK
	 */
	static public boolean startStandardReport (ProcessInfo pi, int WindowNo, boolean IsDirectPrint, MPInstance instance)
	{
		pi.setPrintPreview(!IsDirectPrint);
		return startStandardReport(pi, WindowNo);
	}

	/**
	 *	Start Standard Report.<br/>
	 *  - Get Table Info and submit.<br/>
	 *  A report can be created from:
	 *  <ol>
	 *  <li>attached MPrintFormat, if any (see {@link ProcessInfo#setTransientObject(Object)}, {@link ProcessInfo#setSerializableObject(java.io.Serializable)}
	 *  <li>process information (AD_Process.AD_PrintFormat_ID, AD_Process.AD_ReportView_ID)
	 *  </ol>
	 *  @param pi Process Info
	 *  @return true if OK
	 */
	static public boolean startStandardReport (ProcessInfo pi)
	{
		return startStandardReport(pi, -1);
	}
	
	/**
	 *	Start Standard Report.<br/>
	 *  - Get Table Info and submit.<br/>
	 *  A report can be created from:
	 *  <ol>
	 *  <li>attached MPrintFormat, if any (see {@link ProcessInfo#setTransientObject(Object)}, {@link ProcessInfo#setSerializableObject(java.io.Serializable)}
	 *  <li>process information (AD_Process.AD_PrintFormat_ID, AD_Process.AD_ReportView_ID)
	 *  </ol>
	 *  @param pi Process Info
	 *  @param WindowNo The windows number which invoked the printing
	 *  @return true if OK
	 */
	static public boolean startStandardReport (ProcessInfo pi, int WindowNo)
	{
		return startStandardReport(pi, WindowNo, null);
	}
	
	/**
	 *	Start Standard Report.<br/>
	 *  - Get Table Info and submit.<br/>
	 *  A report can be created from:
	 *  <ol>
	 *  <li>attached MPrintFormat, if any (see {@link ProcessInfo#setTransientObject(Object)}, {@link ProcessInfo#setSerializableObject(java.io.Serializable)}
	 *  <li>process information (AD_Process.AD_PrintFormat_ID, AD_Process.AD_ReportView_ID)
	 *  </ol>
	 *  @param pi Process Info
	 *  @param WindowNo The windows number which invoked the printing
	 * 	@param instance - AD_PInstance
	 *  @return true if OK
	 */
	static public boolean startStandardReport (ProcessInfo pi, int WindowNo, MPInstance instance)
	{
		ReportEngine re = null;
		//
		// Create Report Engine by using attached MPrintFormat (if any)
		Object o = pi.getTransientObject();
		if (o == null)
			o = pi.getSerializableObject();
		if (o != null && o instanceof MPrintFormat) {
			Properties ctx = Env.getCtx();
			MPrintFormat format = (MPrintFormat)o;
			if(instance != null) {
				instance.updatePrintFormatAndLanguageIfEmpty(format);
			}
			String TableName = MTable.getTableName(ctx, format.getAD_Table_ID());
			MQuery query = MQuery.get (ctx, pi.getAD_PInstance_ID(), TableName);
			PrintInfo info = new PrintInfo(pi);
			re = new ReportEngine(ctx, format, query, info, pi.isSummary(), null, WindowNo);
		}
		//
		// Create Report Engine normally
		else {
			re = ReportEngine.get(Env.getCtx(), pi, WindowNo);
			if (re == null)
			{
				pi.setSummary("No ReportEngine");
				return false;
			}
		}

		if (pi.getReportType() != null) {
			re.setReportType(pi.getReportType());
		}
		re.setLanguageID(pi.getLanguageID());
		re.setIsReplaceTabContent(pi.isReplaceTabContent());
		re.setProcessInfo(pi);
		createOutput(re, pi.isPrintPreview(), null);
		return true;
	}	//	startStandardReport

	/**
	 *	Start Financial Report.
	 *  @param pi Process Info
	 *  @return true if OK
	 */
	static public boolean startFinReport (ProcessInfo pi)
	{
		return startFinReport(pi, -1);
	}
	
	/**
	 *	Start Financial Report.
	 *  @param pi Process Info
	 *  @param WindowNo The windows number which invoked the printing
	 *  @return true if OK
	 */
	static public boolean startFinReport (ProcessInfo pi, int WindowNo)
	{
		return startFinReport(pi, WindowNo, null);
	}
	
	/**
	 *	Start Financial Report.
	 *  @param pi Process Info
	 *  @param WindowNo The windows number which invoked the printing
	 * 	@param instance - AD_PInstance
	 *  @return true if OK
	 */
	static public boolean startFinReport (ProcessInfo pi, int WindowNo, MPInstance instance)
	{
		@SuppressWarnings("unused")
		int AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());

		//  Create Query from Parameters
		String TableName = pi.getAD_Process_ID() == 202 ? "T_Report" : "T_ReportStatement";
		MQuery query = MQuery.get (Env.getCtx(), pi.getAD_PInstance_ID(), TableName);

		//	Get PrintFormat
		MPrintFormat format = (MPrintFormat)pi.getTransientObject();
		if (format == null)
			format = (MPrintFormat)pi.getSerializableObject();
		if (format == null)
		{
			s_log.log(Level.SEVERE, "startFinReport - No PrintFormat");
			return false;
		}
		
		if(instance != null) {
			instance.updatePrintFormatAndLanguageIfEmpty(format);
		}
		
		PrintInfo info = new PrintInfo(pi);

		ReportEngine re = new ReportEngine(Env.getCtx(), format, query, info, pi.isSummary(), null, WindowNo);
		if (pi.getReportType() != null) {
			re.setReportType(pi.getReportType());
		}
		
		re.setLanguageID(pi.getLanguageID());
		
		createOutput(re, pi.isPrintPreview(), null);
		return true;
	}	//	startFinReport

	/**
	 * 	Start Document Print for Type.
	 * 	@param type document type in ReportEngine
	 * 	@param Record_ID id
	 * 	@param IsDirectPrint if true, prints directly - otherwise View
	 * 	@return true if success
	 */
	public static boolean startDocumentPrint (int type, int Record_ID, boolean IsDirectPrint)
	{
		return startDocumentPrint(type, Record_ID, null, -1, IsDirectPrint);
	}

	/**
	 * 	Start Document Print for Type with specified printer. Always direct print.
	 * 	@param type document type in ReportEngine
	 *  @param customPrintFormat	Custom print format. Can be null.
	 * 	@param Record_ID id
	 *  @param parent The window which invoked the printing
	 *  @param WindowNo The windows number which invoked the printing
	 * 	@param printerName 	Specified printer name
	 * 	@return true if success
	 */
	public static boolean startDocumentPrint(int type, MPrintFormat customPrintFormat, int Record_ID, IProcessUI parent, int WindowNo, String printerName)
	{
		return(startDocumentPrint(type, customPrintFormat, Record_ID, parent, WindowNo, true, printerName, null));
	}

	/**
	 * 	Start Document Print for Type.
	 * 	@param type document type in ReportEngine
	 * 	@param Record_ID id
	 *  @param parent The window which invoked the printing
	 *  @param WindowNo The windows number which invoked the printing
	 * 	@param IsDirectPrint if true, prints directly - otherwise View
	 * 	@return true if success
	 */
	public static boolean startDocumentPrint(int type, int Record_ID, IProcessUI parent, int WindowNo,
			boolean IsDirectPrint)
	{
		return(startDocumentPrint(type, Record_ID, parent, WindowNo, IsDirectPrint, null ));
	}
	
	/**
	 * 	Start Document Print for Type.
	 * 	@param type document type in ReportEngine
	 * 	@param Record_ID id
	 *  @param parent The window which invoked the printing
	 *  @param WindowNo The windows number which invoked the printing
	 * 	@param IsDirectPrint if true, prints directly - otherwise View
	 * 	@param instance - AD_PInstance
	 * 	@return true if success
	 */
	public static boolean startDocumentPrint(int type, int Record_ID, IProcessUI parent, int WindowNo,
			boolean IsDirectPrint, MPInstance instance)
	{
		return(startDocumentPrint(type, null, Record_ID, parent, WindowNo, IsDirectPrint, null, instance ));
	}

	/**
	 * 	Start Document Print for Type with specified printer.
	 * 	@param type document type in ReportEngine
	 *  @param customPrintFormat
	 * 	@param Record_ID id
	 *  @param parent The window which invoked the printing
	 *  @param WindowNo The windows number which invoked the printing
	 *  @param IsDirectPrint
	 * 	@param printerName 	Specified printer name
	 * 	@param instance - AD_PInstance
	 * 	@return true if success
	 */
	public static boolean startDocumentPrint (int type, MPrintFormat customPrintFormat, int Record_ID, IProcessUI parent, int WindowNo,
			boolean IsDirectPrint, String printerName, MPInstance instance)
	{
		ReportEngine re = ReportEngine.get (Env.getCtx(), type, Record_ID, WindowNo);
		if (re == null)
		{
			throw new AdempiereException("NoDocPrintFormat");
		}
		if (customPrintFormat!=null) {
			// Use custom print format if available
			re.setPrintFormat(customPrintFormat);
		}

		if(re.getPrintFormat()!=null)
		{
			MPrintFormat format = re.getPrintFormat();
			
			if(instance != null) {
				instance.updatePrintFormatAndLanguageIfEmpty(format);
			}
			if(format.getJasperProcess_ID() > 0)
			{
				// We have a Jasper Print Format
				if (!IsDirectPrint)
				{
					//report viewer can handle preview of print format with jasper report process
					preview(re);
				}
				else
				{
					int jasperRecordId = Record_ID;
					if (re.getPrintInfo() != null && re.getPrintInfo().getRecord_ID() > 0)
						jasperRecordId = re.getPrintInfo().getRecord_ID();
					boolean result = ServerReportCtl.runJasperProcess(jasperRecordId, re, IsDirectPrint, printerName);
					if (result && IsDirectPrint)
					{
						ReportEngine.printConfirm(type, Record_ID);
					}
				}
			}
			else
			{
				createOutput(re, !IsDirectPrint, printerName);
				if (IsDirectPrint)
				{
					ReportEngine.printConfirm (type, Record_ID);
				}
			}
		}
		return true;
	}	//	StartDocumentPrint

	/**
	 * 	Start Check Print.<br/>
	 * 	Find/Create.
	 *	@param C_Payment_ID Payment
	 * 	@param IsDirectPrint if true, prints directly - otherwise View
	 * 	@param instance - AD_PInstance
	 * 	@return true if success
	 */
	public static boolean startCheckPrint (int C_Payment_ID, boolean IsDirectPrint, MPInstance instance)
	{

		// afalcone - [ 1871567 ] Wrong value in Payment document
		@SuppressWarnings("unused")
		boolean ok = MPaySelectionCheck.deleteGeneratedDraft(Env.getCtx(), C_Payment_ID, null);
		//

		int C_PaySelectionCheck_ID = 0;
		MPaySelectionCheck psc = MPaySelectionCheck.getOfPayment(Env.getCtx(), C_Payment_ID, null);

		if (psc != null)
			C_PaySelectionCheck_ID = psc.getC_PaySelectionCheck_ID();
		else
		{
			psc = MPaySelectionCheck.createForPayment(Env.getCtx(), C_Payment_ID, null);
			if (psc != null)
				C_PaySelectionCheck_ID = psc.getC_PaySelectionCheck_ID();
		}
		return startDocumentPrint (ReportEngine.CHECK, C_PaySelectionCheck_ID, null, -1, IsDirectPrint, instance);
	}	//	startCheckPrint

	private static void createOutput(ReportEngine re, boolean printPreview, String printerName)
	{
		if (m_pi != null)
			m_pi.setRowCount(re.getPrintData().getRowCount());
		if (printPreview)
			preview(re);
		else {
			if (printerName!=null) {
				re.getPrintInfo().setPrinterName(printerName);
			}
			re.print();
		}
	}

	/**
	 * Launch viewer for report
	 * @param re
	 */
	public static void preview(ReportEngine re)
	{
		ReportViewerProvider viewer = getReportViewerProvider();
		viewer.openViewer(re);
	}

	private static IServiceReferenceHolder<ReportViewerProvider> s_reportViewerProviderReference = null;
	
	/**
	 * Get report viewer provider
	 * @return {@link ReportViewerProvider}
	 */
	public static synchronized ReportViewerProvider getReportViewerProvider() {
		ReportViewerProvider viewer = null;
		if (s_reportViewerProviderReference != null) {
			viewer = s_reportViewerProviderReference.getService();
			if (viewer != null)
				return viewer;
		}
		IServiceReferenceHolder<ReportViewerProvider> viewerReference = Service.locator().locate(ReportViewerProvider.class).getServiceReference();
		if (viewerReference != null) {
			s_reportViewerProviderReference = viewerReference;
			viewer = viewerReference.getService();
		}
		return viewer;
	}

}	//	ReportCtl
