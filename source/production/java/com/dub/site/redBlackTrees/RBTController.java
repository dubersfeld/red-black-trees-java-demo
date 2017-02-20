package com.dub.site.redBlackTrees;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dub.config.annotation.WebController;

/** This is the main controller used in this application */

@WebController
public class RBTController {

	@Inject DisplayNodeFactory displayNodeFactory;
	
	@RequestMapping(value="redBlackTrees")
	public String quicksort() {
		return "redBlackTrees/redBlackTrees";
	}
	
	@RequestMapping(value="act")
	@ResponseBody
	public TreeResponse act(
					@RequestBody TreeRequest message, 
					HttpServletRequest request) 
	{			
		DisplayTree tree = null;
		
		TreeRequest.Type type = message.getType();
		TreeResponse response = new TreeResponse();
		
		// get the Tree instance as session attribute if already created
		HttpSession session = request.getSession();
		if (session.getAttribute("tree") != null) {
			tree = (DisplayTree)session.getAttribute("tree");
		}
		
		if (type == TreeRequest.Type.INIT) {
			List<Integer> keys = message.getKeys();
			System.out.println("INIT begin " + keys);
			tree = new DisplayTree(displayNodeFactory);
				
			for (int i = 0; i < keys.size(); i++) {
				tree.insert(displayNodeFactory.build(keys.get(i), Node.Color.RED));
			}
			
			tree.inOrderWalk(tree.getRoot());// debug only
			
			tree.breadthFirstWalk(response.getTreeArray());
			
			response.setType(TreeResponse.Type.CREATED);
			
			session.setAttribute("tree", tree);// add to session scope
			
			return response;	
			
		} else if (type == TreeRequest.Type.SEARCH) {
			int searchkey = message.getKey();
			
			System.out.println("SEARCH " + searchkey);
				
			Node<Geometry> node = tree.search(tree.getRoot(), searchkey);
			if (node != tree.getNil()) {
				response.setType(TreeResponse.Type.FOUND);
			} else {
				response.setType(TreeResponse.Type.NOT_FOUND);
			}
			
			return response;	
		} else if (type == TreeRequest.Type.DELETE) {
			
			int key = message.getKey();
			System.out.println("DELETE " + key);
			
			String status = tree.checkRemove(key, response.getTreeArray());
		
			if (status.equals("NF")) {
				response.setType(TreeResponse.Type.NOT_FOUND);
			} else if (status.equals("OK")) {
				response.setType(TreeResponse.Type.OK);
			}
				
			return response;	
		} else if (type == TreeRequest.Type.INSERT) {
					
			int newkey = message.getKey();
			
			String status = tree.checkInsert(newkey, response.getTreeArray());
			
			if (status.equals("OK")) {
				response.setType(TreeResponse.Type.OK);
			} else if (status.equals("ND")) {
				response.setType(TreeResponse.Type.FORBIDDEN);
			} else if (status.equals("NP")) {
				response.setType(TreeResponse.Type.NODE_PRESENT);
			}
			
			return response;	
		} else {
			response.setType(TreeResponse.Type.ERROR);
			return response;
		}
	
	}
	
}
