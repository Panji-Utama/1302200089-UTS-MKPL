package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class InfoPribadi {
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	private boolean isForeigner;
	private Gender gender;

	public InfoPribadi(String firstName, String lastName, String idNumber, String address, boolean isForeigner,
			Gender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.isForeigner = isForeigner;
		this.gender = gender;
	}
}

public class TglMasuk {
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;

	public TglMasuk(int yearJoined, int monthJoined, int dayJoined) {
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
	}

}

public class Employee {

	public enum Gender {
		Laki,
		Perempuan
	}

	private String employeeId;
	private InfoPribadi infoPribadi;
	private TglMasuk tglMasuk;

	private int monthWorkingInYear;
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;

	public Employee(String employeeId, InfoPribadi infoPribadi, TglMasuk tglMasuk) {
		this.employeeId = employeeId;
		this.infoPribadi = infoPribadi;
		this.tglMasuk = tglMasuk;

		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya
	 * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3:
	 * 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */

	public void setMonthlySalary(int grade) {
		int[] salaries = { 3000000, 5000000, 7000000 };
		int salary = salaries[grade - 1];
		if (isForeigner) {
			salary *= 1.5;
		}
		monthlySalary = salary;
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}

	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	public int getAnnualIncomeTax() {

		// Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah
		// bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();

		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		} else {
			monthWorkingInYear = 12;
		}

		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible,
				spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
