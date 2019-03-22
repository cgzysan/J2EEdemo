package ysan.ftp_sys.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import jdk.nashorn.api.scripting.URLReader;
import sun.swing.FilePane;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet("/fileServlet")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取请求参数
		String method = request.getParameter("method");
		if ("upload".equals(method)) {
			// 上传
			upload(request, response);
		}
		else if ("downList".equals(method)) {
			// 下载列表
			downList(request, response);
		}
		else if ("down".equals(method)) {
			// 下载
			down(request, response);
		}
	}

	/**
	 * 1. 上传
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 创建工厂对象
			FileItemFactory factory = new DiskFileItemFactory();
			// 2. 文件上传核心工具类
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置大小限制
//		upload.setFileSizeMax(10 * 1024 * 1024); // 单个文件大小限制
//		upload.setSizeMax(50 * 1024 * 1024); // 总文件大小限制
			upload.setHeaderEncoding("UTF-8"); // 对中文文件编码处理

			if (ServletFileUpload.isMultipartContent(request)) {
				// 3. 把请求数据转换为list集合
				ServletRequestContext ctx = new ServletRequestContext(request);
				List<FileItem> list = upload.parseRequest(ctx);
				// 遍历
				for (FileItem fileItem : list) {
					// 判断：普通文本数据
					if (fileItem.isFormField()) {
						// 获取名称
						String name = fileItem.getFieldName();
						// 获取值
						String value = fileItem.getString();
						System.out.println("Value = " + value);
					}
					// 文件表单项
					else {
						/******** 文件上传 *********/
						// a. 获取文件名称
						String fileName = fileItem.getName();
						// ------ 处理上传文件重名问题 ------
						// a1. 先给标记
						String id = UUID.randomUUID().toString();
						// a2. 拼接文件名
						fileName = id + "#" + fileName;

						// b. 获取上传目录
						String filePath = getServletContext().getRealPath("/upload");
						// c. 创建要上传的文件对象
						File file = new File(filePath, fileName);
						// d. 上传
						fileItem.write(file);
						fileItem.delete(); // 删除组件运行时产生的临时文件
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 2. 进入下载列表
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void downList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先获取upload目录下所有文件的文件名，再保存；跳转到down.jsp列表展示
		// 1.初始化map集合 Map<包含唯一标记的文件名, 简短文件名>
		Map<String, String> fileNames = new HashMap<>();
		// 2.获取上传目录，及其下所有的文件的文件名
		String basePath = getServletContext().getRealPath("/upload");
		System.out.println("获取文件的路径 ：" + basePath);
		// 目录
		File dir = new File(basePath);
		String[] list = dir.list();
		// 遍历，封装
		if (list != null && list.length > 0) {
			for (int i = 0; i < list.length; i++) {
				// 全名
				String fileName = list[i];
				// 短名
				String shortName = fileName.substring(fileName.lastIndexOf("#") + 1);
				fileNames.put(fileName, shortName);
			}
		}
		// 保存到request域
		request.setAttribute("fileNames", fileNames);
		// 转发
		request.getRequestDispatcher("/downlist.jsp").forward(request, response);
	}

	private void down(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 获取用户下载的文件名称
		String fileName = request.getParameter("fileName");
		
		String basePath = getServletContext().getRealPath("/upload");
		InputStream in = new FileInputStream(new File(basePath, fileName));
		
		//如果文件名是中文，需要进行url编码
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("content-disposition", "attachment;fileName=" + fileName);
		
		OutputStream out = response.getOutputStream();
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = in.read(b)) != -1) {
			out.write(b, 0, len);
		}
		// 关闭
		out.close();
		in.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
