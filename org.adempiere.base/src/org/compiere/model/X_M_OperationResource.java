/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_OperationResource
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="M_OperationResource")
public class X_M_OperationResource extends PO implements I_M_OperationResource, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241222L;

    /** Standard Constructor */
    public X_M_OperationResource (Properties ctx, int M_OperationResource_ID, String trxName)
    {
      super (ctx, M_OperationResource_ID, trxName);
      /** if (M_OperationResource_ID == 0)
        {
			setM_OperationResource_ID (0);
			setM_ProductOperation_ID (0);
			setName (null);
			setSetupTime (Env.ZERO);
			setTeardownTime (Env.ZERO);
			setUnitRuntime (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_M_OperationResource (Properties ctx, int M_OperationResource_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, M_OperationResource_ID, trxName, virtualColumns);
      /** if (M_OperationResource_ID == 0)
        {
			setM_OperationResource_ID (0);
			setM_ProductOperation_ID (0);
			setName (null);
			setSetupTime (Env.ZERO);
			setTeardownTime (Env.ZERO);
			setUnitRuntime (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_M_OperationResource (Properties ctx, String M_OperationResource_UU, String trxName)
    {
      super (ctx, M_OperationResource_UU, trxName);
      /** if (M_OperationResource_UU == null)
        {
			setM_OperationResource_ID (0);
			setM_ProductOperation_ID (0);
			setName (null);
			setSetupTime (Env.ZERO);
			setTeardownTime (Env.ZERO);
			setUnitRuntime (Env.ZERO);
        } */
    }

    /** Standard Constructor */
    public X_M_OperationResource (Properties ctx, String M_OperationResource_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, M_OperationResource_UU, trxName, virtualColumns);
      /** if (M_OperationResource_UU == null)
        {
			setM_OperationResource_ID (0);
			setM_ProductOperation_ID (0);
			setName (null);
			setSetupTime (Env.ZERO);
			setTeardownTime (Env.ZERO);
			setUnitRuntime (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_M_OperationResource (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_M_OperationResource[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException
	{
		return (org.compiere.model.I_A_Asset)MTable.get(getCtx(), org.compiere.model.I_A_Asset.Table_ID)
			.getPO(getA_Asset_ID(), get_TrxName());
	}

	/** Set Asset.
		@param A_Asset_ID Asset used internally or by customers
	*/
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1)
			set_Value (COLUMNNAME_A_Asset_ID, null);
		else
			set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Asset.
		@return Asset used internally or by customers
	  */
	public int getA_Asset_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Job getC_Job() throws RuntimeException
	{
		return (org.compiere.model.I_C_Job)MTable.get(getCtx(), org.compiere.model.I_C_Job.Table_ID)
			.getPO(getC_Job_ID(), get_TrxName());
	}

	/** Set Position.
		@param C_Job_ID Job Position
	*/
	public void setC_Job_ID (int C_Job_ID)
	{
		if (C_Job_ID < 1)
			set_Value (COLUMNNAME_C_Job_ID, null);
		else
			set_Value (COLUMNNAME_C_Job_ID, Integer.valueOf(C_Job_ID));
	}

	/** Get Position.
		@return Job Position
	  */
	public int getC_Job_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Comment/Help.
		@param Help Comment or Hint
	*/
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp()
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Operation Resource.
		@param M_OperationResource_ID Product Operation Resource
	*/
	public void setM_OperationResource_ID (int M_OperationResource_ID)
	{
		if (M_OperationResource_ID < 1)
			set_ValueNoCheck (COLUMNNAME_M_OperationResource_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_M_OperationResource_ID, Integer.valueOf(M_OperationResource_ID));
	}

	/** Get Operation Resource.
		@return Product Operation Resource
	  */
	public int getM_OperationResource_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_OperationResource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set M_OperationResource_UU.
		@param M_OperationResource_UU M_OperationResource_UU
	*/
	public void setM_OperationResource_UU (String M_OperationResource_UU)
	{
		set_Value (COLUMNNAME_M_OperationResource_UU, M_OperationResource_UU);
	}

	/** Get M_OperationResource_UU.
		@return M_OperationResource_UU	  */
	public String getM_OperationResource_UU()
	{
		return (String)get_Value(COLUMNNAME_M_OperationResource_UU);
	}

	public org.compiere.model.I_M_ProductOperation getM_ProductOperation() throws RuntimeException
	{
		return (org.compiere.model.I_M_ProductOperation)MTable.get(getCtx(), org.compiere.model.I_M_ProductOperation.Table_ID)
			.getPO(getM_ProductOperation_ID(), get_TrxName());
	}

	/** Set Product Operation.
		@param M_ProductOperation_ID Product Manufacturing Operation
	*/
	public void setM_ProductOperation_ID (int M_ProductOperation_ID)
	{
		if (M_ProductOperation_ID < 1)
			set_ValueNoCheck (COLUMNNAME_M_ProductOperation_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_M_ProductOperation_ID, Integer.valueOf(M_ProductOperation_ID));
	}

	/** Get Product Operation.
		@return Product Manufacturing Operation
	  */
	public int getM_ProductOperation_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductOperation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name Alphanumeric identifier of the entity
	*/
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName()
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair()
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Setup Time.
		@param SetupTime Setup time before starting Production
	*/
	public void setSetupTime (BigDecimal SetupTime)
	{
		set_Value (COLUMNNAME_SetupTime, SetupTime);
	}

	/** Get Setup Time.
		@return Setup time before starting Production
	  */
	public BigDecimal getSetupTime()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_SetupTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Teardown Time.
		@param TeardownTime Time at the end of the operation
	*/
	public void setTeardownTime (BigDecimal TeardownTime)
	{
		set_Value (COLUMNNAME_TeardownTime, TeardownTime);
	}

	/** Get Teardown Time.
		@return Time at the end of the operation
	  */
	public BigDecimal getTeardownTime()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TeardownTime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Runtime per Unit.
		@param UnitRuntime Time to produce one unit
	*/
	public void setUnitRuntime (BigDecimal UnitRuntime)
	{
		set_Value (COLUMNNAME_UnitRuntime, UnitRuntime);
	}

	/** Get Runtime per Unit.
		@return Time to produce one unit
	  */
	public BigDecimal getUnitRuntime()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_UnitRuntime);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}