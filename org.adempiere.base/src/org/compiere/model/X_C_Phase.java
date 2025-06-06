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

/** Generated Model for C_Phase
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="C_Phase")
public class X_C_Phase extends PO implements I_C_Phase, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241222L;

    /** Standard Constructor */
    public X_C_Phase (Properties ctx, int C_Phase_ID, String trxName)
    {
      super (ctx, C_Phase_ID, trxName);
      /** if (C_Phase_ID == 0)
        {
			setC_Phase_ID (0);
			setC_ProjectType_ID (0);
			setName (null);
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM C_Phase WHERE C_ProjectType_ID=@C_ProjectType_ID@
			setStandardQty (Env.ZERO);
// 1
        } */
    }

    /** Standard Constructor */
    public X_C_Phase (Properties ctx, int C_Phase_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, C_Phase_ID, trxName, virtualColumns);
      /** if (C_Phase_ID == 0)
        {
			setC_Phase_ID (0);
			setC_ProjectType_ID (0);
			setName (null);
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM C_Phase WHERE C_ProjectType_ID=@C_ProjectType_ID@
			setStandardQty (Env.ZERO);
// 1
        } */
    }

    /** Standard Constructor */
    public X_C_Phase (Properties ctx, String C_Phase_UU, String trxName)
    {
      super (ctx, C_Phase_UU, trxName);
      /** if (C_Phase_UU == null)
        {
			setC_Phase_ID (0);
			setC_ProjectType_ID (0);
			setName (null);
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM C_Phase WHERE C_ProjectType_ID=@C_ProjectType_ID@
			setStandardQty (Env.ZERO);
// 1
        } */
    }

    /** Standard Constructor */
    public X_C_Phase (Properties ctx, String C_Phase_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, C_Phase_UU, trxName, virtualColumns);
      /** if (C_Phase_UU == null)
        {
			setC_Phase_ID (0);
			setC_ProjectType_ID (0);
			setName (null);
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM C_Phase WHERE C_ProjectType_ID=@C_ProjectType_ID@
			setStandardQty (Env.ZERO);
// 1
        } */
    }

    /** Load Constructor */
    public X_C_Phase (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_C_Phase[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Standard Phase.
		@param C_Phase_ID Standard Phase of the Project Type
	*/
	public void setC_Phase_ID (int C_Phase_ID)
	{
		if (C_Phase_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_Phase_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_Phase_ID, Integer.valueOf(C_Phase_ID));
	}

	/** Get Standard Phase.
		@return Standard Phase of the Project Type
	  */
	public int getC_Phase_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Phase_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_Phase_UU.
		@param C_Phase_UU C_Phase_UU
	*/
	public void setC_Phase_UU (String C_Phase_UU)
	{
		set_Value (COLUMNNAME_C_Phase_UU, C_Phase_UU);
	}

	/** Get C_Phase_UU.
		@return C_Phase_UU	  */
	public String getC_Phase_UU()
	{
		return (String)get_Value(COLUMNNAME_C_Phase_UU);
	}

	public org.compiere.model.I_C_ProjectType getC_ProjectType() throws RuntimeException
	{
		return (org.compiere.model.I_C_ProjectType)MTable.get(getCtx(), org.compiere.model.I_C_ProjectType.Table_ID)
			.getPO(getC_ProjectType_ID(), get_TrxName());
	}

	/** Set Project Type.
		@param C_ProjectType_ID Type of the project
	*/
	public void setC_ProjectType_ID (int C_ProjectType_ID)
	{
		if (C_ProjectType_ID < 1)
			set_ValueNoCheck (COLUMNNAME_C_ProjectType_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_ProjectType_ID, Integer.valueOf(C_ProjectType_ID));
	}

	/** Get Project Type.
		@return Type of the project
	  */
	public int getC_ProjectType_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectType_ID);
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

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
	{
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_ID)
			.getPO(getM_Product_ID(), get_TrxName());
	}

	/** Set Product.
		@param M_Product_ID Product, Service, Item
	*/
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1)
			set_Value (COLUMNNAME_M_Product_ID, null);
		else
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
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

	/** Set Sequence.
		@param SeqNo Method of ordering records; lowest number comes first
	*/
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Standard Quantity.
		@param StandardQty Standard Quantity
	*/
	public void setStandardQty (BigDecimal StandardQty)
	{
		set_Value (COLUMNNAME_StandardQty, StandardQty);
	}

	/** Get Standard Quantity.
		@return Standard Quantity
	  */
	public BigDecimal getStandardQty()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_StandardQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}