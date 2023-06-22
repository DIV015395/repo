package com.prm.scripts;

import com.prm.pageobjects.utils.TestData;
import com.prm.patientdashboard.pageobject.*;

import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.prm.docdashboard.pageobject.BasePage;
import com.prm.docdashboard.pageobject.DoctorDashBoard;
import com.prm.docdashboard.pageobject.LoginPage;
import com.prm.docdashboard.pageobject.PatientDashboardPage;
import com.relevantcodes.extentreports.LogStatus;
import com.prm.pageobjects.utils.ExtentReport;
import com.prm.pageobjects.utils.ReadConfig;
import com.prm.pageobjects.utils.SheetTest;

/**
 * 
 * @author Ajit Yadav
 *
 */
public class OralExamsTestCase {
	private LoginPage loginPage = new LoginPage();
	private DoctorDashBoard doctorDashboard = new DoctorDashBoard(loginPage);
	private BasePage basePage = new BasePage(loginPage);
	private ExtentReport extentReport = new ExtentReport(loginPage, "OralExamsTestCase");
	private PatientDashboardPage patientDashboardPage = new PatientDashboardPage(loginPage);
	private BasePatientLifeCyclePage basePatientLifeCyclePage = new BasePatientLifeCyclePage(loginPage);
	private OralExamsPage OralExamsPage = new OralExamsPage(loginPage);
	private OralExamListingPage oralExamListingPage = new OralExamListingPage(loginPage);
	private ChiefComplaintAddPage chiefComplaintAddPage = new ChiefComplaintAddPage(loginPage);
	private ChiefComplaintListingPage chiefComplaintListingPage = new ChiefComplaintListingPage(loginPage);

	/*input data for validation*/
//	private static final String PATIENT_NAME= TestData.getInstance().getInputData("oral_exam_patient_name");
//	private static final String MOBILE_NUMBER = TestData.getInstance().getInputData("oral_exam_patient_mobile");
//	private static final String DOCTOR_NIK_NAME = TestData.getInstance().getInputData("oral_exam_doctor_nikName");
//	private static final String CLINIC_NAME = TestData.getInstance().getInputData("oral_exam_clinic_name");

	/*---------------Messages for Extent Reports-------------------*/
	private static final String SCRIPTS_STARTED_MSG = "@@ Test scripts has been started here @@";
	private static final String STATUS_OF_SCRIPTS = "@@ Test script has been completed @@";
	private static final String VERIFY_UI_ORAL_EXAM_ADD = "Validated oral Exam Add page UI Page on first time navigation - #verifyUIOralExamAdd";
	private static final String VERIFY_ORAL_EXAM_LIST_UI_WITHOUT_ORAL_EXAM = "Validated Oral Exam List UI Without OralExam - #verifyOralExamListUIWithoutOralExam";
	private static final String VERIFY_WEB_ELEMENT_ON_TEETH_POPUP = "Validated UI of teeth popup after clicking on teeth image - #verifyWebElementOnTeethPopup";
	private static final String BEHAVIOUR_CHIEF_COMPLAINT_ON_ORAL_EXAM = "Validated chief complaints behaviour with oral exam - #behaviourChiefComplaintOnOralExam";
	private static final String TEETH_BEHAVIOUR_ORAL_EXAM_ADD = "Validated teeth functionality on oral Add page - #teethBehaviourOralExamAdd";
	private static final String BEHAVIOR_OF_TISSUE_FOR_SINGLE_TEETH = "Validated behavior Of Tissue For Single Teeth - #behaviorOfTissueForSingleTeeth";
	private static final String ADD_ORAL_EXAM_SINGLE_TEETH = "Validated add Oral Exam for Single Teeth - #addOralExamSingleTeeth";
	private static final String ORAL_EXAM_OF_MULTIPLE_TEETH = "Validated add Oral Exam for multiple Teeth - #addOralExamOfMultipleTeeth";
	private static final String ORAL_EXAM_EDIT_INPUT_LIST = " Validated oral Exam Edit from Input List - #oralExamEditInputList";
	Map<String, String> patntOralExamData = null;

	@BeforeClass
	public void setup() {
		patntOralExamData = SheetTest.prepareData("OralExamData","OralExam","A1","Z");
		extentReport.logger = extentReport.report.startTest(this.getClass().getSimpleName());
		extentReport.logger.log(LogStatus.INFO, LoginPage.testCaseRunningStatusmsg);
		extentReport.logger.log(LogStatus.PASS, LoginPage.browserMsg);
		String username = ReadConfig.getInstance().getUsername().toString();
		String password = ReadConfig.getInstance().getPassword().toString();
		loginPage.login(username, password);
		extentReport.logger.log(LogStatus.PASS, LoginPage.userNameMsg);
		extentReport.logger.log(LogStatus.PASS, LoginPage.passwordMsg);
		extentReport.logger.log(LogStatus.PASS, LoginPage.loginBtnClickMsg);
		doctorDashboard.doctorDashboardHomePage();
		basePage.selectClinicFrmHeader(patntOralExamData.get("clinicLocation"));
		basePage.enterMobileNo(patntOralExamData.get("patient_mobile"));
		basePage.clickOnSearchBtn();
		basePage.clickOnPatient(patntOralExamData.get("patient_mobile"),patntOralExamData.get("patient_name"));
		Assert.assertTrue(basePage.verification().contains("Patient Dashboard"));
	}

	@BeforeMethod
	public void verifyPatientdashBoardHomePage() {
		extentReport.logger.log(LogStatus.PASS, SCRIPTS_STARTED_MSG);
		if(patientDashboardPage.dueWarningPopup.size()>0){
			patientDashboardPage.hideDueWarningPopup();
		}else {
			basePatientLifeCyclePage.clickOnAlert();
		}
	}

	@Test(enabled = true, priority = 1)
	public void verifyUIOralExamAdd() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_UI_ORAL_EXAM_ADD);
		patientDashboardPage.clickOnOralExamAdd();
		basePatientLifeCyclePage.clickOnAlert();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.webElementOfLeftNavigator();
		OralExamsPage.verifyUiOralExamPage();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		OralExamsPage.verifyUiOralExamPage();
		basePatientLifeCyclePage.verifyPatientName(patntOralExamData.get("patient_name"));
		basePatientLifeCyclePage.headerOnAddPage("Oral Exams");
		OralExamsPage.verifyAdultsTeeths();
		OralExamsPage.clickOnPedo();
		OralExamsPage.verifyPedoTeeths();
		OralExamsPage.clickOnMixed();
		OralExamsPage.verifyMixedTeeths();
		OralExamsPage.clickOnTissue();
		OralExamsPage.verifyWebElementInSoftTissue();
		OralExamsPage.clickOnHardTissue();
		OralExamsPage.verifyWebElementInHardTissue();
		Assert.assertTrue(basePage.verification().contains("Oral Exams"));
	}

	@Test(enabled = true, priority = 2)
	public void verifyOralExamListUIWithoutOralExam() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_ORAL_EXAM_LIST_UI_WITHOUT_ORAL_EXAM);
		patientDashboardPage.clickOnOralExamList();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.webElementOfLeftNavigator();
		basePatientLifeCyclePage.openCloseLeftNavigator();
		basePatientLifeCyclePage.dashBoardBtnVerify();
		basePatientLifeCyclePage.verifyAddNewBtn();
		basePatientLifeCyclePage.headerOnListPage("Oral Exam Listing");
		basePatientLifeCyclePage.verifyPatientName(patntOralExamData.get("patient_name"));
		/* Only For First Time Traversing */
//		basePatientLifeCyclePage.noRecordMsgDisplayed();
		Assert.assertTrue(basePage.verification().contains("Oral Exam Listing"));
	}

	@Test(enabled = true, priority = 3)
	public void verifyWebElementOnTeethPopup() {
		extentReport.logger.log(LogStatus.PASS, VERIFY_WEB_ELEMENT_ON_TEETH_POPUP);
		patientDashboardPage.clickOnOralExamAdd();
		basePatientLifeCyclePage.clickOnAlert();
		OralExamsPage.selectMultipleTeeth("Mixed", "18");
		OralExamsPage.selectMultipleTeeth("Mixed", "22");
		OralExamsPage.clickOnTeethImage("Mixed", "21");
		OralExamsPage.clickOnYes();
		OralExamsPage.verifySelectedTeethOnPopup("18", 1);
		OralExamsPage.verifySelectedTeethOnPopup("22", 2);
		OralExamsPage.verifySelectedTeethOnPopup("21", 3);
		OralExamsPage.verifyTitleOfPopup("mixed", "Tooth Site & Perio");
		OralExamsPage.clickOnBuccal();
		OralExamsPage.verifyProvisinals();
		OralExamsPage.clickOnDistal();
		OralExamsPage.verifyProvisinals();
		OralExamsPage.clickOnMesial();
		OralExamsPage.verifyProvisinals();
		OralExamsPage.clickOnOcclusal();
		OralExamsPage.verifyProvisinals();
		OralExamsPage.clickOnPalatal();
		OralExamsPage.verifyProvisinals();
		OralExamsPage.clickOnAllSurface();
		OralExamsPage.verifyProvisinals();
		OralExamsPage.clickOnPerio();
		OralExamsPage.verifyProvisinals();
		OralExamsPage.verifyIoparBtn();
		OralExamsPage.clickOnIOPARBtn();
		OralExamsPage.verifyIOPARNotes();
		OralExamsPage.verifyToothObservationRemark();
		OralExamsPage.clickOnSoftTissueOnPopup();
		OralExamsPage.verifyTitleOfPopup("mixed", "Soft Tissue");
		OralExamsPage.clickOnLip();
		OralExamsPage.verifySoftProvisinals();
		OralExamsPage.clickOnCheek();
		OralExamsPage.verifySoftProvisinals();
		OralExamsPage.clickOnTongue();
		OralExamsPage.verifySoftProvisinals();
		OralExamsPage.clickOnFloorOfMouth();
		OralExamsPage.verifySoftProvisinals();
		OralExamsPage.clickOnPalate();
		OralExamsPage.verifySoftProvisinals();
		OralExamsPage.clickOnGingiva();
		OralExamsPage.verifySoftProvisinals();
		OralExamsPage.clickOnVestibule();
		OralExamsPage.verifySoftProvisinals();
		OralExamsPage.clickOnFrenum();
		OralExamsPage.verifySoftProvisinals();
		OralExamsPage.clickOnSalivaryGland();
		OralExamsPage.verifySoftProvisinals();
		OralExamsPage.clickOnLymphNodes();
		OralExamsPage.verifySoftProvisinals();
		OralExamsPage.verifyToothObservationRemark();
		OralExamsPage.clickOnHardTissueInPopup();
		OralExamsPage.verifyTitleOfPopup("mixed", "Hard Tissue");
		OralExamsPage.clickOnMandibularAngle();
		OralExamsPage.verifyHardProvisionals();
		OralExamsPage.clickOnMandibularBody();
		OralExamsPage.verifyHardProvisionals();
		OralExamsPage.clickOnMaxillaryTuberosity();
		OralExamsPage.verifyHardProvisionals();
		OralExamsPage.clickOnPosteriorMaxilla();
		OralExamsPage.verifyHardProvisionals();
		OralExamsPage.clickOnPreMaxilla();
		OralExamsPage.verifyHardProvisionals();
		OralExamsPage.clickOnMaxillarySinus();
		OralExamsPage.verifyHardProvisionals();
		OralExamsPage.clickOnMandibularSymphysis();
		OralExamsPage.verifyHardProvisionals();
		OralExamsPage.clickOnTMJoint();
		OralExamsPage.verifyHardProvisionals();
		OralExamsPage.verifyToothObservationRemark();
		OralExamsPage.clickOnCloseBtn();
		Assert.assertTrue(basePage.verification().contains("Oral Exams"));
	}

	@Test(enabled = true, priority = 4)
	public void behaviourChiefComplaintOnOralExam() {
		extentReport.logger.log(LogStatus.PASS, BEHAVIOUR_CHIEF_COMPLAINT_ON_ORAL_EXAM);
		patientDashboardPage.clickOnOralExamAdd();
		basePatientLifeCyclePage.clickOnAlert();
		OralExamsPage.clickOnchiefComplaintsAddBtn();
		basePatientLifeCyclePage.clickOnAlert();
		chiefComplaintAddPage.clickOnChiefComplaint("Plaque");
		chiefComplaintAddPage.clickOnChiefComplaint("Bad Breath");
		chiefComplaintAddPage.clickOnChiefComplaint("Bleeding Gums");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnChiefComplaintlistBtn();
//		basePatientLifeCyclePage.verifyDates();
		chiefComplaintListingPage.chiefComplaintInMainList("Plaque");
		chiefComplaintListingPage.chiefComplaintInMainList("Bad Breath");
		chiefComplaintListingPage.chiefComplaintInMainList("Bleeding Gums");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnOralExamAdd();
		basePatientLifeCyclePage.clickOnAlert();
		OralExamsPage.checkedChiefComplaintsOnOralExam("Plaque");
		OralExamsPage.checkedChiefComplaintsOnOralExam("Bad Breath");
		OralExamsPage.checkedChiefComplaintsOnOralExam("Bleeding Gums");
		OralExamsPage.clickOnEditOfChiefComplaint();
		basePatientLifeCyclePage.clickOnAlert();
		chiefComplaintAddPage.clickOnChiefComplaint("Teeth Grinding");
		chiefComplaintAddPage.clickOnChiefComplaint("Chewing Difficulty");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnChiefComplaintlistBtn();
//		basePatientLifeCyclePage.verifyDates();
		chiefComplaintListingPage.chiefComplaintInMainList("Plaque");
		chiefComplaintListingPage.chiefComplaintInMainList("Bad Breath");
		chiefComplaintListingPage.chiefComplaintInMainList("Bleeding Gums");
		chiefComplaintListingPage.chiefComplaintInMainList("Teeth Grinding");
		chiefComplaintListingPage.chiefComplaintInMainList("Chewing Difficulty");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnOralExamAdd();
		basePatientLifeCyclePage.clickOnAlert();
		OralExamsPage.checkedChiefComplaintsOnOralExam("Plaque");
		OralExamsPage.checkedChiefComplaintsOnOralExam("Bad Breath");
		OralExamsPage.checkedChiefComplaintsOnOralExam("Bleeding Gums");
		OralExamsPage.checkedChiefComplaintsOnOralExam("Teeth Grinding");
		OralExamsPage.checkedChiefComplaintsOnOralExam("Chewing Difficulty");
		OralExamsPage.removeChiefComplaintsOnOralExam("Plaque");
		OralExamsPage.checkedDeletedChiefComplaints("Plaque");
		OralExamsPage.removeChiefComplaintsOnOralExam("Teeth Grinding");
		OralExamsPage.checkedDeletedChiefComplaints("Teeth Grinding");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnChiefComplaintlistBtn();
//		basePatientLifeCyclePage.verifyDates();
		chiefComplaintListingPage.deletedChiefComplaintInMainList("Plaque");
		chiefComplaintListingPage.deletedChiefComplaintInMainList("Teeth Grinding");
		basePatientLifeCyclePage.clickViewBtn(patntOralExamData.get("clinicLocation"));
//		chiefComplaintListingPage.verifyDateInView();
		chiefComplaintListingPage.dataNameInView();
		chiefComplaintListingPage.deletedChiefComplaintInView("Plaque");
		chiefComplaintListingPage.deletedChiefComplaintInView("Teeth Grinding");
		chiefComplaintListingPage.checkedChiefComplaintInView("Bad Breath");
		chiefComplaintListingPage.checkedChiefComplaintInView("Bleeding Gums");
		chiefComplaintListingPage.checkedChiefComplaintInView("Chewing Difficulty");
		chiefComplaintListingPage.userNameInView("Bad Breath", patntOralExamData.get("doctorNickName"));
		chiefComplaintListingPage.closeViewPopup();
		Assert.assertTrue(basePage.verification().contains("Chief Complaint Listing"));
	}

	@Test(enabled = true, priority = 5)
	public void teethBehaviourOralExamAdd() {
		extentReport.logger.log(LogStatus.PASS, TEETH_BEHAVIOUR_ORAL_EXAM_ADD);
		patientDashboardPage.clickOnOralExamAdd();
		basePatientLifeCyclePage.clickOnAlert();
		OralExamsPage.allTeethButtonDeselected();
		OralExamsPage.clickOnAllTeeth();
		OralExamsPage.allTeethButtonSelected();
		OralExamsPage.clickOnPedo();
		OralExamsPage.clickOnAdult();
		OralExamsPage.allTeethButtonDeselected();
		OralExamsPage.clickOnPedo();
		OralExamsPage.allTeethButtonDeselected();
		OralExamsPage.clickOnAllTeeth();
		OralExamsPage.allTeethButtonSelected();
		OralExamsPage.clickOnMixed();
		OralExamsPage.clickOnPedo();
		OralExamsPage.allTeethButtonDeselected();
		OralExamsPage.clickOnMixed();
		OralExamsPage.allTeethButtonDeselected();
		OralExamsPage.clickOnAllTeeth();
		OralExamsPage.allTeethButtonSelected();
		OralExamsPage.clickOnAdult();
		OralExamsPage.clickOnMixed();
		OralExamsPage.allTeethButtonDeselected();
		OralExamsPage.clickOnAdult();
		OralExamsPage.selectMultipleTeeth("Adult", "27");
		OralExamsPage.selectMultipleTeeth("Adult", "28");
		OralExamsPage.clickOnMixed();
		OralExamsPage.selectedInRespective("Mixed", "27");
		OralExamsPage.selectedInRespective("Mixed", "28");
		OralExamsPage.clickOnAdult();
		OralExamsPage.selectedInRespective("Adult", "27");
		OralExamsPage.selectedInRespective("Adult", "28");
		OralExamsPage.clickOnPedo();
		OralExamsPage.selectMultipleTeeth("Pedo", "64");
		OralExamsPage.selectMultipleTeeth("Pedo", "65");
		OralExamsPage.clickOnMixed();
		OralExamsPage.deSelectedInRespective("Mixed", "27");
		OralExamsPage.deSelectedInRespective("Mixed", "28");
		OralExamsPage.selectedInRespective("Mixed", "64");
		OralExamsPage.selectedInRespective("Mixed", "65");
		OralExamsPage.clickOnPedo();
		OralExamsPage.selectedInRespective("Pedo", "64");
		OralExamsPage.selectedInRespective("Pedo", "65");
		OralExamsPage.clickOnMixed();
		OralExamsPage.selectedInRespective("Mixed", "64");
		OralExamsPage.selectedInRespective("Mixed", "65");
		OralExamsPage.selectMultipleTeeth("Mixed", "11");
		OralExamsPage.selectMultipleTeeth("Mixed", "12");
		OralExamsPage.clickOnAdult();
		OralExamsPage.selectedInRespective("Adult", "11");
		OralExamsPage.selectedInRespective("Adult", "12");
		OralExamsPage.clickOnMixed();
		OralExamsPage.deSelectedInRespective("Mixed", "64");
		OralExamsPage.deSelectedInRespective("Mixed", "65");
		OralExamsPage.selectedInRespective("Mixed", "11");
		OralExamsPage.selectedInRespective("Mixed", "12");
		OralExamsPage.selectMultipleTeeth("Mixed", "61");
		OralExamsPage.selectMultipleTeeth("Mixed", "62");
		OralExamsPage.clickOnPedo();
		OralExamsPage.selectedInRespective("Pedo", "61");
		OralExamsPage.selectedInRespective("Pedo", "62");
		OralExamsPage.clickOnMixed();
		OralExamsPage.selectedInRespective("Mixed", "61");
		OralExamsPage.selectedInRespective("Mixed", "62");
		OralExamsPage.deSelectedInRespective("Mixed", "11");
		OralExamsPage.deSelectedInRespective("Mixed", "12");
		Assert.assertTrue(basePage.verification().contains("Oral Exams"));
	}

	@Test(enabled = true, priority = 6)
	public void behaviorOfTissueForSingleTeeth() {
		extentReport.logger.log(LogStatus.PASS, BEHAVIOR_OF_TISSUE_FOR_SINGLE_TEETH);
		patientDashboardPage.clickOnOralExamAdd();
		basePatientLifeCyclePage.clickOnAlert();
		OralExamsPage.clickOnTeethImage("Adult", "24");
		OralExamsPage.verifySelectedTeethOnPopup("24", 1);
		OralExamsPage.clickOnBuccal();
		OralExamsPage.clickOnToothSiteAndPerioProvisinals();
		OralExamsPage.verifyProvisinalIsSelected("Buccal");
		OralExamsPage.selectedToothAndSiteProvisionalDisplayedBelow();
		OralExamsPage.clickOnDistal();
		OralExamsPage.clickOnToothSiteAndPerioProvisinals();
		OralExamsPage.verifyProvisinalIsSelected("Distal");
		OralExamsPage.selectedToothAndSiteProvisionalDisplayedBelow();
		OralExamsPage.clickOnMesial();
		OralExamsPage.clickOnToothSiteAndPerioProvisinals();
		OralExamsPage.verifyProvisinalIsSelected("Mesial");
		OralExamsPage.selectedToothAndSiteProvisionalDisplayedBelow();
		OralExamsPage.clickOnOcclusal();
		OralExamsPage.clickOnToothSiteAndPerioProvisinals();
		OralExamsPage.verifyProvisinalIsSelected("Occlusal");
		OralExamsPage.selectedToothAndSiteProvisionalDisplayedBelow();
		OralExamsPage.clickOnPalatal();
		OralExamsPage.clickOnToothSiteAndPerioProvisinals();
		OralExamsPage.verifyProvisinalIsSelected("Palatal");
		OralExamsPage.selectedToothAndSiteProvisionalDisplayedBelow();
		OralExamsPage.clickOnAllSurface();
		OralExamsPage.clickOnToothSiteAndPerioProvisinals();
		OralExamsPage.verifyProvisinalIsSelected("All Surface");
		OralExamsPage.selectedToothAndSiteProvisionalDisplayedBelow();
		OralExamsPage.clickOnPerio();
		OralExamsPage.clickOnToothSiteAndPerioProvisinals();
		OralExamsPage.verifyProvisinalIsSelected("Perio");
		OralExamsPage.selectedToothAndSiteProvisionalDisplayedBelow();
		OralExamsPage.clickOnSoftTissueOnPopup();
		OralExamsPage.clickOnLip();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfSoftTissue("Lip");
		OralExamsPage.selectedSoftTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnCheek();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfSoftTissue("Cheek");
		OralExamsPage.selectedSoftTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnTongue();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfSoftTissue("Tongue");
		OralExamsPage.selectedSoftTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnFloorOfMouth();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfSoftTissue("Floor of Mouth");
		OralExamsPage.selectedSoftTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnPalate();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfSoftTissue("Palate");
		OralExamsPage.selectedSoftTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnGingiva();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfSoftTissue("Gingiva");
		OralExamsPage.selectedSoftTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnVestibule();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfSoftTissue("Vestibule");
		OralExamsPage.selectedSoftTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnFrenum();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfSoftTissue("Frenum");
		OralExamsPage.selectedSoftTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnSalivaryGland();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfSoftTissue("Salivary Gland");
		OralExamsPage.selectedSoftTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnLymphNodes();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfSoftTissue("Lymph Nodes");
		OralExamsPage.selectedSoftTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnHardTissueInPopup();
		OralExamsPage.clickOnMandibularAngle();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfHardTissue("Mandibular Angle");
		OralExamsPage.selectedHardTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnMandibularBody();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfHardTissue("Mandibular Body");
		OralExamsPage.selectedHardTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnMaxillaryTuberosity();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfHardTissue("Maxillary Tuberosity");
		OralExamsPage.selectedHardTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnPosteriorMaxilla();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfHardTissue("Posterior Maxilla");
		OralExamsPage.selectedHardTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnPreMaxilla();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfHardTissue("Pre-Maxilla");
		OralExamsPage.selectedHardTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnMaxillarySinus();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfHardTissue("Maxillary Sinus");
		OralExamsPage.selectedHardTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnMandibularSymphysis();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfHardTissue("Mandibular Symphysis");
		OralExamsPage.selectedHardTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnTMJoint();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.provisionalIsSelectedOfHardTissue("TM Joint");
		OralExamsPage.selectedHardTissueProvisionalDisplayedBelow();
		OralExamsPage.clickOnCloseBtn();
		Assert.assertTrue(basePage.verification().contains("Oral Exams"));
	}

	@Test(enabled = true, priority = 7)
	public void addOralExamSingleTeeth() {
		extentReport.logger.log(LogStatus.PASS, ADD_ORAL_EXAM_SINGLE_TEETH);
		patientDashboardPage.clickOnOralExamAdd();
		basePatientLifeCyclePage.clickOnAlert();
		OralExamsPage.clickOnTeethImage("Adult", "47");
		OralExamsPage.verifySelectedTeethOnPopup("47", 1);
		OralExamsPage.clickOnBuccal();
		OralExamsPage.clickOnToothSiteAndPerioProvisinals();
		OralExamsPage.clickOnDistal();
		OralExamsPage.clickOnToothSiteAndPerioProvisinals();
		OralExamsPage.clickOnSoftTissueOnPopup();
		OralExamsPage.clickOnLip();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.clickOnCheek();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.clickOnHardTissueInPopup();
		OralExamsPage.clickOnPreMaxilla();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.clickOnMaxillarySinus();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.enterOralExamNotes("it for testing");
		OralExamsPage.clickOnSaveBtn();
		OralExamsPage.verifyInputListWebElement();
		OralExamsPage.verifyProvisinalsInputList("47", 6);
		OralExamsPage.verifyNoteInputList("47", "it for testing");
		OralExamsPage.verifyActionBtnOfTeethNo("47");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnOralExamList();
//		basePatientLifeCyclePage.verifyDates();
		basePatientLifeCyclePage.headerOnListPage("Oral Exam Listing");
		basePatientLifeCyclePage.verifyPatientName(patntOralExamData.get("patient_name"));
		basePatientLifeCyclePage.actionBtnMainList(patntOralExamData.get("clinicLocation"));
		oralExamListingPage.checkedDataName();
		oralExamListingPage.verifyProvisinalsSingleTeethOralExamList("47", 6);
		oralExamListingPage.verifyCreatedByInOralExamList("47",patntOralExamData.get("doctorNickName"));
		oralExamListingPage.verifyNoteOralExamList("47", "it for testing");
		basePatientLifeCyclePage.clickViewBtn(patntOralExamData.get("clinicLocation"));
		basePatientLifeCyclePage.headerViewPopup("Oral Exam View");
		oralExamListingPage.checkedViewDataName();
		oralExamListingPage.oralExamViewPopup("47", 6);
		oralExamListingPage.createdByOralExamViewPopup("47",patntOralExamData.get("doctorNickName"));
		oralExamListingPage.noteOralExamViewPopup("47", "it for testing");
		basePatientLifeCyclePage.closeViewPopup();
		Assert.assertTrue(basePage.verification().contains("Oral Exam Listing"));
	}

	@Test(enabled = true, priority = 8)
	public void addOralExamOfMultipleTeeth() {
		extentReport.logger.log(LogStatus.PASS, ORAL_EXAM_OF_MULTIPLE_TEETH);
		patientDashboardPage.clickOnOralExamAdd();
		basePatientLifeCyclePage.clickOnAlert();
		OralExamsPage.selectMultipleTeeth("Mixed", "44");
		OralExamsPage.selectMultipleTeeth("Mixed", "45");
		OralExamsPage.clickOnTeethImage("Mixed", "45");
		OralExamsPage.clickOnBuccal();
		OralExamsPage.clickOnToothSiteAndPerioProvisinals();
		OralExamsPage.clickOnDistal();
		OralExamsPage.clickOnToothSiteAndPerioProvisinals();
		OralExamsPage.clickOnSoftTissueOnPopup();
		OralExamsPage.clickOnLip();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.clickOnCheek();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.clickOnHardTissueInPopup();
		OralExamsPage.clickOnPreMaxilla();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.clickOnMaxillarySinus();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.enterOralExamNotes("it for testing");
		OralExamsPage.clickOnSaveBtn();
		OralExamsPage.verifyInputListWebElement();
		OralExamsPage.provisinalsInputListOfMultipleTeeth("44", "45", 6);
		OralExamsPage.verifyNoteInputList("44", "it for testing");
		OralExamsPage.verifyActionBtnOfTeethNo("44");
		basePatientLifeCyclePage.clickOnDashBoard();
		basePatientLifeCyclePage.clickOnAlert();
		patientDashboardPage.clickOnOralExamList();
//		basePatientLifeCyclePage.verifyDates();
		basePatientLifeCyclePage.headerOnListPage("Oral Exam Listing");
		basePatientLifeCyclePage.verifyPatientName(patntOralExamData.get("patient_name"));
		basePatientLifeCyclePage.actionBtnMainList(patntOralExamData.get("clinicLocation"));
		oralExamListingPage.checkedDataName();
		oralExamListingPage.verifyProvisinalsMultipleTeethOralExamList("44", "45", 6);
		oralExamListingPage.verifyNoteOralExamList("44", "it for testing");
		oralExamListingPage.verifyCreatedByInOralExamList("44",patntOralExamData.get("doctorNickName"));
		basePatientLifeCyclePage.clickViewBtn(patntOralExamData.get("clinicLocation"));
		basePatientLifeCyclePage.headerViewPopup("Oral Exam View");
		oralExamListingPage.checkedViewDataName();
		oralExamListingPage.oralExamViewPopup("44", 6);
		oralExamListingPage.createdByOralExamViewPopup("44", patntOralExamData.get("doctorNickName"));
		oralExamListingPage.noteOralExamViewPopup("44", "it for testing");
		basePatientLifeCyclePage.closeViewPopup();
		Assert.assertTrue(basePage.verification().contains("Oral Exam Listing"));
	}

	@Test(enabled = true, priority = 9)
	public void oralExamEditInputList() {
		extentReport.logger.log(LogStatus.PASS, ORAL_EXAM_EDIT_INPUT_LIST);
		patientDashboardPage.clickOnOralExamAdd();
		basePatientLifeCyclePage.clickOnAlert();
		OralExamsPage.clickOnTeethImage("Mixed", "41");
		OralExamsPage.clickOnBuccal();
		OralExamsPage.clickOnToothSiteAndPerioProvisinals();
		OralExamsPage.clickOnSaveBtn();
		OralExamsPage.clickOnEditBtnTeethNo("41");
		OralExamsPage.selectedAfterClickOnEdit("Mixed", "41");
		OralExamsPage.clickOnTeethImage("Mixed", "41");
		OralExamsPage.removeToothSiteProvisionals();
		OralExamsPage.clickOnSaveBtn();
		OralExamsPage.alertMsgOnNotSelectProvisional();
		OralExamsPage.clickOnCloseBtn();
		OralExamsPage.clickOnTeethImage("Mixed", "42");
		OralExamsPage.clickOnSoftTissueOnPopup();
		OralExamsPage.clickOnCheek();
		OralExamsPage.clickOnSoftTissueProvisinals();
		OralExamsPage.clickOnSaveBtn();
		OralExamsPage.clickOnEditBtnTeethNo("42");
		OralExamsPage.selectedAfterClickOnEdit("Mixed", "42");
		OralExamsPage.clickOnTeethImage("Mixed", "42");
		OralExamsPage.clickOnSoftTissueOnPopup();
		OralExamsPage.removeSoftTissueProvisionals();
		OralExamsPage.clickOnSaveBtn();
		OralExamsPage.alertMsgOnNotSelectProvisional();
		OralExamsPage.clickOnCloseBtn();
		OralExamsPage.clickOnTeethImage("Mixed", "43");
		OralExamsPage.clickOnHardTissueInPopup();
		OralExamsPage.clickOnMandibularAngle();
		OralExamsPage.clickOnHardTissueProvisinals();
		OralExamsPage.clickOnSaveBtn();
		OralExamsPage.clickOnEditBtnTeethNo("43");
		OralExamsPage.selectedAfterClickOnEdit("Mixed", "43");
		OralExamsPage.clickOnTeethImage("Mixed", "43");
		OralExamsPage.clickOnHardTissueInPopup();
		OralExamsPage.removeHardTissueProvisionals();
		OralExamsPage.clickOnSaveBtn();
		OralExamsPage.alertMsgOnNotSelectProvisional();
		OralExamsPage.clickOnCloseBtn();
		OralExamsPage.verifyProvisinalsInputList("41", 1);
		OralExamsPage.verifyProvisinalsInputList("42", 1);
		OralExamsPage.verifyProvisinalsInputList("43", 1);
		Assert.assertTrue(basePage.verification().contains("Oral Exams"));
		loginPage.softAssert().assertAll();
	}

	@AfterMethod(description = STATUS_OF_SCRIPTS, enabled = true)
	public void statusOfScripts(ITestResult result) {
		extentReport.onTestFailure(result);
		extentReport.logger.log(LogStatus.PASS, STATUS_OF_SCRIPTS);
		basePatientLifeCyclePage.clickOnDashBoard();
	}

	@AfterClass
	public void tearDown() {
		extentReport.report.endTest(extentReport.logger);
		extentReport.report.flush();
		// extentReport.report.close();
		basePage.clickOnlogOut();
	}
}
