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

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.ValueNamePair;

/** Generated Model for B_Seller
 *  @author iDempiere (generated)
 *  @version Release 12 - $Id$ */
@org.adempiere.base.Model(table="B_Seller")
public class X_B_Seller extends PO implements I_B_Seller, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20241222L;

    /** Standard Constructor */
    public X_B_Seller (Properties ctx, int B_Seller_ID, String trxName)
    {
      super (ctx, B_Seller_ID, trxName);
      /** if (B_Seller_ID == 0)
        {
			setAD_User_ID (0);
			setIsInternal (false);
			setName (null);
			setValidTo (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Standard Constructor */
    public X_B_Seller (Properties ctx, int B_Seller_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, B_Seller_ID, trxName, virtualColumns);
      /** if (B_Seller_ID == 0)
        {
			setAD_User_ID (0);
			setIsInternal (false);
			setName (null);
			setValidTo (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Standard Constructor */
    public X_B_Seller (Properties ctx, String B_Seller_UU, String trxName)
    {
      super (ctx, B_Seller_UU, trxName);
      /** if (B_Seller_UU == null)
        {
			setAD_User_ID (0);
			setIsInternal (false);
			setName (null);
			setValidTo (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Standard Constructor */
    public X_B_Seller (Properties ctx, String B_Seller_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, B_Seller_UU, trxName, virtualColumns);
      /** if (B_Seller_UU == null)
        {
			setAD_User_ID (0);
			setIsInternal (false);
			setName (null);
			setValidTo (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_B_Seller (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_B_Seller[")
        .append(get_UUID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
	{
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_ID)
			.getPO(getAD_User_ID(), get_TrxName());
	}

	/** Set User/Contact.
		@param AD_User_ID User within the system - Internal or Business Partner Contact
	*/
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1)
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set B_Seller_UU.
		@param B_Seller_UU B_Seller_UU
	*/
	public void setB_Seller_UU (String B_Seller_UU)
	{
		set_Value (COLUMNNAME_B_Seller_UU, B_Seller_UU);
	}

	/** Get B_Seller_UU.
		@return B_Seller_UU	  */
	public String getB_Seller_UU()
	{
		return (String)get_Value(COLUMNNAME_B_Seller_UU);
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

	/** Set Internal.
		@param IsInternal Internal Organization
	*/
	public void setIsInternal (boolean IsInternal)
	{
		set_Value (COLUMNNAME_IsInternal, Boolean.valueOf(IsInternal));
	}

	/** Get Internal.
		@return Internal Organization
	  */
	public boolean isInternal()
	{
		Object oo = get_Value(COLUMNNAME_IsInternal);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
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

    /** Get Record UU/ColumnName
        @return UU/ColumnName pair
      */
    public ValueNamePair getValueNamePair()
    {
        return new ValueNamePair(get_UUID(), getName());
    }

	/** Set Valid to.
		@param ValidTo Valid to including this date (last day)
	*/
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo()
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}
}