-- 
SELECT register_migration_script('202412041755_IDEMPIERE-6334.sql') FROM dual;

SET SQLBLANKLINES ON
SET DEFINE OFF

-- Dec 4, 2024, 5:55:11 PM CET
UPDATE AD_Tab SET IsAdvancedTab='Y',Updated=TO_TIMESTAMP('2024-12-04 17:55:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=200329
;

