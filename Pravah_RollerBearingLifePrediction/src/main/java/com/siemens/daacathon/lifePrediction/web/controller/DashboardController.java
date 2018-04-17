package com.siemens.daacathon.lifePrediction.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.siemens.daacathon.lifePrediction.utility.DBUtil;
import com.siemens.daacathon.lifePrediction.web.data.LiveData;
import com.siemens.daacathon.lifePrediction.web.data.configData;

@Controller
@ControllerAdvice
public class DashboardController {

	configData data = new configData();

	private LiveData liveData = new LiveData();

	private double inverseServiceLife = 0;
	private int serviceLifeCount = 0;

	@RequestMapping(value = "/configPage", method = RequestMethod.GET)
	public ModelAndView configPage(HttpSession session) {
		ModelAndView model = null;
		if (session.getAttribute("user") != null)
			model = new ModelAndView("configPage");
		else
			model = new ModelAndView("redirect:/index");
		return model;
	}

	@RequestMapping(value = "/submitConfigData", method = RequestMethod.POST)
	public String submitConfigData(@RequestBody configData data) {
		this.data = data;
		return "dashboard";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(HttpSession session) {
		ModelAndView model = null;
		if (session.getAttribute("user") != null)
			model = new ModelAndView("dashboard");
		else
			model = new ModelAndView("redirect:/index");
		return model;
	}

	@RequestMapping(value = "/getFirstId", method = RequestMethod.POST)
	public synchronized ResponseEntity<String> submitFirstId() {
		return new ResponseEntity<String>("{\"id\":" + DBUtil.executeFirstIdQuery() + "}", HttpStatus.OK);
	}

	@RequestMapping(value = "/getLiveData", method = RequestMethod.POST)
	public synchronized ResponseEntity<String> submitConfigData(@RequestBody int i, ModelMap model) {
		this.liveData = DBUtil.executeQuery(i);

		if (liveData == null) {
			return new ResponseEntity<String>("{\"result\":\"stopped\"", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("{\"id\":" + liveData.getId() + "," + "\"pressure\":"
					+ liveData.getPressure() + "," + "\"rpm\":" + liveData.getRpm() + "," + "\"temperature\":"
					+ liveData.getTemperature() + "," + "\"load\":" + +equivalentDynamicLoad() + ","
					+ "\"expansionFactor\":" + expansionFactor(equivalentDynamicLoad()) + "," + "\"lubricationFactor\":"
					+ lubricationFactor() + "," + "\"adjustedRating\":" + adjustedRating() + ","
					+ "\"serviceLifeConsumed\":" + getServiceLifeConsumed() + "," + "\"serviceLifeLeft\":"
					+ getServiceLifeLeft() + "}", HttpStatus.OK);

	}

	/*
	 * @RequestMapping(value = "/getCalculatedData", method = RequestMethod.POST)
	 * public synchronized ResponseEntity<HttpStatus> submitCalculatedData(ModelMap
	 * model) { if(liveData == null) { model.addAttribute("load", 0);
	 * model.addAttribute("expansionFactor",0);
	 * model.addAttribute("lubricationFactor", 0);
	 * model.addAttribute("adjustedRating", 0);
	 * model.addAttribute("serviceLifeConsumed", 0); } else {
	 * model.addAttribute("load", equivalentDynamicLoad());
	 * model.addAttribute("expansionFactor",expansionFactor(equivalentDynamicLoad())
	 * ); model.addAttribute("lubricationFactor", lubricationFactor());
	 * model.addAttribute("adjustedRating", adjustedRating());
	 * model.addAttribute("serviceLifeConsumed", getServiceLifeConsumed()); }
	 * 
	 * return new ResponseEntity<HttpStatus>(HttpStatus.OK); return new
	 * ResponseEntity<String>("{\"load\":" + equivalentDynamicLoad() + "," +
	 * "\"expansionFactor\":" + expansionFactor(equivalentDynamicLoad()) + "," +
	 * "\"lubricationFactor\":" + lubricationFactor() + "," + "\"adjustedRating\":"
	 * + adjustedRating() + "," + "\"serviceLifeConsumed\":" +
	 * getServiceLifeConsumed() + "}", HttpStatus.OK); }
	 */

	private double equivalentDynamicLoad() {
		// Fdr radial dynamic load
		double Fdr = radialDynamicLoad();

		double x = data.getFactorX();
		double y = data.getFactorY();

		// Fsr radial static load
		double Fm = data.getRotorMass();
		double Fsr = (Fm * 1465) / 2240;

		// Fr resultant radial load
		double Fr = Math.sqrt((Fdr * Fdr) + (Fsr * Fsr));

		// Fa resultant axial dynamic load
		double Fa = 0.1 * ((2 * Fdr) + Fm);

		// P equivalent dynamic load
		double P = (x * Fr) + (y * Fa);

		return P;
	}

	private double radialDynamicLoad() {
		double Rrc = data.getCylinderDiameter() / 2;
		double Nrc = data.getCylinderNo();
		double sf = data.getBearingSafetyFactor();
		double Fdr = (3.14 * Rrc * Rrc * liveData.getPressure() * Nrc * sf * 0.001);
		return Fdr;
	}

	private double expansionFactor(double P) {
		double C = data.getBearingDynamicLoad();
		double p = data.getLifeExponent();
		double L10n = (16666 / liveData.getRpm()) * Math.pow((C / P), p);
		return L10n;
	}

	private double lubricationFactor() {
		double Dpw = (data.getBearingID() + data.getBearingOD()) / 2;
		double a = 0.0864 * (Math.pow(2, 0.68)) * (Math.pow(Dpw, 0.55));
		if (a > 1)
			a = 1;
		double eC = a * (1 - (0.6796 / Math.pow(Dpw, 0.33)));
		return eC;
	}

	private double adjustedRating() {
		double eC = lubricationFactor();
		double x = 1.5859 - (1.23478 / Math.pow(2, 0.071739));
		double y = Math.pow(((eC * data.getBearingFatigueLoad()) / equivalentDynamicLoad()), 0.4);
		double z = 1 - (x * y);
		double Aiso = 0.1 * Math.pow(z, -9.185);
		return Aiso;
	}

	private double getServiceLifeConsumed() {
		return expansionFactor(equivalentDynamicLoad()) * adjustedRating();
	}

	private double getServiceLifeLeft() {
		serviceLifeCount++;
		double slc = getServiceLifeConsumed();
		inverseServiceLife += 1 / slc;
		return serviceLifeCount / inverseServiceLife;
	}
}
